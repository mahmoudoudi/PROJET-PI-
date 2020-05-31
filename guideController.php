<?php


namespace BackBundle\Controller;


use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;

class guideController extends Controller
{
    public function afficherguideAction()
    {

        $em = $this->getDoctrine()->getManager();
        $guide = $em->getRepository('gestioneventBundle:guide')->findAll();

        //$modele=$em->getRepository(Modele::class)->findAll();


        return $this->render('@Back/Default/guide.html.twig', array("guide" => $guide));
    }

    public function supprimerguideAction($idGuide)
    {
        $em=$this->getDoctrine()->getManager();
        $guides =$em->getRepository('gestioneventBundle:guide')->find($idGuide);
        $em->remove($guides);
        $em->flush();
        return $this->redirectToRoute('guideback');
    }

    public  function  modifierguideAction(Request $request ,$idGuide)
    {
        $em=$this->getDoctrine()->getManager();
        $guide=$em->getRepository('gestioneventBundle:guide')->find($idGuide);
        $form =$this->createFormBuilder($guide)
            ->add('NameG')
            ->add('LastNameG')
            ->add('EmailG')
            ->add('PhoneG')
            ->add('EventAccept')
            ->add('modifier',SubmitType::class)
            ->getForm();
        $form->handleRequest($request);
        if ($form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($guide);
            $em->flush();
            return $this->redirectToRoute('guideback');
        }

        return $this->render('@Back/Default/updateguide.html.twig',array('form'=>$form->createView()));

    }
}