<?php

namespace FrontBundle\Controller;

use FrontBundle\Entity\Comment;
use FrontBundle\Entity\Contact;
use FrontBundle\Entity\News;
use FrontBundle\Entity\User;
use FrontBundle\Form\CommentType;
use FrontBundle\Form\ContactType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
//use Symfony\Component\HttpFoundation\JsonResponse;
//use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
//use Symfony\Component\Serializer\Serializer;


class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Front/Default/index.html.twig');
    }
    public function evenementAction()
    {
        return $this->render('@Front/Default/evenement.html.twig');
    }
    public function contactAction()
    {
        return $this->render('@Front/Default/contact.html.twig');
    }
    public function loginAction()
    {
        return $this->render('@Front/Default/login.html.twig');
    }
    public function signupAction()
    {
        return $this->render('@Front/Default/signup.html.twig');
    }
    public function AjoutecAction(Request $request)
    {
        $contact=new Contact();
        $Form=$this->createForm(contactType::class,$contact);
        $Form->handleRequest($request);
        if($Form->isSubmitted() && $Form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $user = $this->get('security.token_storage')->getToken()->getUser();
            $contact->setIdclient($user);
            $contact->setEmailcontact($this->get('security.token_storage')->getToken()->getUser()->getEmail());
            $em->persist($contact);
            $em->flush();
            return $this->redirectToRoute('_addContact');}
        return $this->render('@Front/Default/contact.html.twig', array(
            'form'=>$Form->createView()
            // ...
        ));
    }

    public function shownAction()
    {
        $em=$this->getDoctrine()->getManager();
        $news=$em->getRepository( News::class)->findAll();


        return $this->render('@Front/Default/News.html.twig', array(
            'n'=>$news
            // ...
        ));
    }
    public function seemorenAction($id,Request $request)
    {
        $Comment=new Comment();

        $em=$this->getDoctrine()->getManager();
        $news=$em->getRepository( News::class)->find($id);
        $commentt=$em->getRepository( Comment::class)->findBy(array('idnews' => $id));
        $rnews=$em->getRepository( News::class)->findAll();
        $Formm=$this->createForm(commentType::class,$Comment);
        $Formm->handleRequest($request);

        if($Formm->isSubmitted() && $Formm->isValid()){
            $em=$this->getDoctrine()->getManager();
            $user = $this->get('security.token_storage')->getToken()->getUser();
            $Comment->setIdclient($user);
            $Comment->setIdnews($news);
            $Comment->setDatec(new \DateTime('now'));
            $em->persist($Comment);
            $em->flush();
            return $this->redirectToRoute('_SeeMoreNews', array('id' => $id));}

        return $this->render('@Front/Default/SeeMoreN.html.twig', array(
            'n'=>$news,
            'formm'=>$Formm->createView(),
            'c'=>$commentt,
            'r'=>$rnews
            // ...
        ));
    }
    public function DeletecAction($id,$id2)
    {
        $em=$this->getDoctrine()->getManager();
        $news=$em->getRepository( Comment::class)->find($id);
        $em->remove($news);
        $em->flush();
        return $this->redirectToRoute('_SeeMoreNews', array('id' => $id2));
    }

    public function allAction()
    {
        $news = $this->getDoctrine()->getManager()
            ->getRepository('FrontBundle:News')
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($news);
        return new JsonResponse($formatted);
    }
    public function new1Action(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $contact = new Contact();
        $user = $this->get('security.token_storage')->getToken()->getUser();
        $contact->setTextcontact($request->get('text'));
        $contact->setIdclient($user);
        $em->persist($contact);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($contact);
        return new JsonResponse($formatted);
    }
public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $contact = new Contact();
        $iduser = $request->query->get('iduser');
        $contact->setTextcontact($request->get('text'));
        $user=$em->getRepository(User::class)->find($iduser);
        $email=$user->getEmail();
        $contact->setEmailcontact($email);
        $contact->setIdclient($user);
        $em->persist($contact);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($contact);
        return new JsonResponse($formatted);
    }

}
