<?php


namespace BackBundle\Controller;

use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use gestioneventBundle\Entity\events;
use gestioneventBundle\Form\eventsType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Swift_Mailer;
use Swift_SmtpTransport;
use FrontBundle\Entity\User;


class EventController extends Controller

{

    public function affichereventAction()
    {

        $em = $this->getDoctrine()->getManager();
        $events = $em->getRepository('gestioneventBundle:events')->findAll();

        //$modele=$em->getRepository(Modele::class)->findAll();


        return $this->render('@Back/Default/eventback.html.twig', array("events" => $events));

    }

    public function supprimereventAction($idEvent)
    {
        $em=$this->getDoctrine()->getManager();
        $eventes =$em->getRepository('gestioneventBundle:events')->find($idEvent);
        $em->remove($eventes);
        $em->flush();
        return $this->redirectToRoute('eventback');
    }


    public function ajoutereventaction(\Symfony\Component\HttpFoundation\Request $request)
    {
        $events= new events();
        $form =$this->createFormBuilder($events)
            ->add('NameEvent')
            ->add('AddressEvent')
            ->add('Type')
            ->add('PriceEvent')
            ->add('NbrPlace')
            ->add('DescriptionEvent')
            ->add('Image',FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('dateD')
            ->add('dateF')
            ->add('save',SubmitType::class)
            ->getForm();
        $form->handleRequest($request);
        if ($form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            /** @var UploadedFile $file */
            $file = $events->getImage();
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $filename);
            $events->setImage($filename);
            $em->persist($events);
            $em->flush();
            return $this->redirectToRoute('eventback');
        }
        return $this->render('@Back/Default/ajoutevent.html.twig',array('form'=>$form->createView()));
    }
    public function statAction(){
        $pieChart = new PieChart();
        $em= $this->getDoctrine()->getManager();
        $query = $em->createQuery('SELECT e  ,UPPER(e.nameEvent) as nom ,COUNT(e.nbrPlace) as num FROM gestioneventBundle:events e
 GROUP BY e.nameEvent');
        $reservations=$query->getScalarResult();
        $data= array();
        $stat=['evenement', 'id'];
        $i=0;
        array_push($data,$stat);

        $ln= count($reservations);
        for ($i=0 ;$i<count($reservations);$i++){
            $stat=array();
            array_push($stat,$reservations[$i]['nom'],$reservations[$i]['num']/$ln);
            $stat=[$reservations[$i]['nom'],$reservations[$i]['num']*100/$ln];

            array_push($data,$stat);
        }
        $pieChart->getData()->setArrayToDataTable( $data );
        $pieChart->getOptions()->setTitle('Pourcentages des participants par evenement');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);
        return $this->render('@Back\Default\chartEvent.html.twig', array('piechart' => $pieChart));
    }

    public  function  modifierEventAction(Request $request ,$idEvent)
    {
        $event= new events();
        $em=$this->getDoctrine()->getManager();
        $event=$em->getRepository(events::class)->find($idEvent);

        $form=$this->createForm(eventsType::class,$event)->handleRequest($request);
        $form->getData();

        if ($form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            /** @var UploadedFile $file */
            $file = $event->getImage();

            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $filename);
            $event->setImage($filename);
            $em->persist($event);
            $em->flush();
            return $this->redirectToRoute('eventback');
        }

        return $this->render('@Back/Default/updatevent.html.twig',array('form'=>$form->createView()));

    }

    public  function SendMailAction(Request $request,$idEvent,Swift_Mailer $mailer=null)
    {
        $event= new events();
        $em=$this->getDoctrine()->getManager();
        $event=$em->getRepository(events::class)->find($idEvent);

        $users=$em->getRepository(User::class)->findAll();
        foreach ($users as $user)
        {
            $transport = Swift_SmtpTransport::newInstance('smtp.googlemail.com',465, 'ssl')
                ->setUsername('mohamedyassine.mahmoudi@esprit.tn')
                ->setPassword('t23346871');
            $mailer = Swift_Mailer::newInstance($transport);
            $message = (new \Swift_Message('New Event Invitation !'))
                ->setFrom('mohamedyassine.mahmoudi@esprit.tn')
                ->setTo($user->getEmail())
                ->setContentType("text/html");
            $img = $message->embed(\Swift_Image::fromPath($event->getImagePath()));
            $message->setBody( $this->renderView(
                'email.html.twig',
                array('event' => $event,'img' => $img,'user'=>$user)
            ));
            $mailer->send($message);
            $this->get('mailer')->send($message);



        }
        return $this->redirectToRoute('eventback');







    }





}