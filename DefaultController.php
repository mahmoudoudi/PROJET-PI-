<?php

namespace BackBundle\Controller;

use FrontBundle\Entity\Contact;
use FrontBundle\Entity\Email;
use FrontBundle\Entity\News;
use FrontBundle\Entity\User;
use FrontBundle\Form\EmailType;
use FrontBundle\Form\NewsType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Back/Default/index.html.twig');
    }
    public function newsAction()
    {
        $em=$this->getDoctrine()->getManager();
        $news=$em->getRepository( News::class)->findAll();
        $contacts=$em->getRepository( Contact::class)->findAll();
        return $this->render('@Back/Default/News.html.twig',array(
            'c'=>$news,
            'n'=>$contacts
            // ...
        ));
    }

    public function DeleteAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $news=$em->getRepository( news::class)->find($id);
        $em->remove($news);
        $em->flush();
        return $this->redirectToRoute('_affiche');
    }
    public function AjouteAction(Request $request)
    {
        $news=new news();
        $Form=$this->createForm(NewsType::class,$news);
        $Form->handleRequest($request);
        if($Form->isSubmitted() && $Form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($news);
            $em->flush();
            return $this->redirectToRoute('_affiche');}
        return $this->render('@Back/Default/addnews.html.twig', array(
            'f'=>$Form->createView()
            // ...
        ));
    }
    public function UpdateAction($id,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $club=$em->getRepository( news::class)->find($id);;
        // $club=new Club();
        $Form=$this->createForm(NewsType::class,$club);
        $Form->handleRequest($request);
        if($Form->isSubmitted()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('_affiche');
        }
        return $this->render('@Back/Default/EditNews.html.twig', array(
            'f'=>$Form->createView()
            // ...
        ));
    }
    public function sendAction($email,Request $request)
    {
        $mail=new Email();
        $getDoctrine = $this->getDoctrine()->getManager();
        $Form=$this->createForm(EmailType::class,$mail);
        $Form->handleRequest($request);

        if($Form->isSubmitted() && $Form->isValid()) {

            $subject = $mail->getSubject();
            $text = $mail->getTextmail();
            $message = (new \Swift_Message($subject))
                ->setFrom('mohannad.khadhraoui@esprit.tn')
                ->setTo($email)
                ->setBody($text);


            $this->get('mailer')->send($message);
            return $this->redirectToRoute('_affiche');
            // or, you can also fetch the mailer service this way
            // $this->get('mailer')->send($message);
        }
        return $this->render('@Back/Default/Sendmail.html.twig', array(
            'form'=>$Form->createView()
            // ...
        ));
    }


}
