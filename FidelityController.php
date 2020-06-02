<?php

namespace FrontBundle\Controller;

use FrontBundle\Entity\Fidelitycard;
//use http\Env\Request;
use FrontBundle\Entity\User;

use FrontBundle\FrontBundle;
use Symfony\Component\HttpFoundation\Response;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class FidelityController extends Controller
{
    public function AfficherFideleAction()
    {     $id = $this->get('security.token_storage')->getToken()->getUser();
        $m2 = $this->getDoctrine()->getManager();
        $User= $m2->getRepository(Fidelitycard::class)->findBY(array('idclient' => $id));
  return $this->render('@Front/Fidelity/Afficher_fidele.html.twig',array('points'=>$User
    ));
    }

   public function checkAction()
   {


       $m2 = $this->getDoctrine()->getManager();
       $id = $this->get('security.token_storage')->getToken()->getUser();
       $User = $m2->getRepository(Fidelitycard::class)->findBY(array('idclient' => $id));
       if ($User) {
           return new Response("Mon espace de Fidelité");

       } else
           return new Response("Obtenir une carte de fidelité!");


   }
    public function check3Action()   {return new Response("Obtenir une carte de fidelité!");  }

    public function AddFideleAction()
    {
        if ($this->isGranted('ROLE_USER') == false) {
            return $this->redirectToRoute('fos_user_registration_register');
        }
        $user1 = $this->get('security.token_storage')->getToken()->getUser();
        $m2 = $this->getDoctrine()->getManager();
        $fidelitycard = new Fidelitycard();
       $User= $m2->getRepository(Fidelitycard::class)->findBY(['idclient' => $user1]);


            if ($User)
            {
                return $this->redirectToRoute('Afficher_fidele');
            }

            else
                $fidelitycard->setPoints(0);
            $fidelitycard->setIdclient($user1);
            $m2->persist($fidelitycard);
            $m2->flush();
            return $this->redirectToRoute('front_homepage');
        }









}