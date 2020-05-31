<?php

namespace BackBundle\Controller;
use BackBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ClientController extends Controller
{
    public function AfficherclAction()
    {
        $m2 = $this->getDoctrine()->getManager();
        $Wishlist = $m2->getRepository("FrontBundle:Wishlist")->findAll();
        $m3 = $this->getDoctrine()->getManager();
        $Fidelity = $m3->getRepository("FrontBundle:Fidelitycard")->findAll();
        $m = $this->getDoctrine()->getManager();
        $User = $m->getRepository("FrontBundle:User")->findAll();
        return $this->render('@Back/Client/affichercl.html.twig', array(
                'User'=> $User,'Wishlist'=>$Wishlist,'Fidelity'=>$Fidelity
            )
        );
    }

    public function SuprimerAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("FrontBundle:Fidelitycard")->find($id);
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('_affichercl');
    }
        public function SuppprimerAction($id1)
    {
        $em = $this->getDoctrine()->getManager();
        $user1 = $em->getRepository("FrontBundle:Fidelitycard")->find($id1);
        $em->remove($user1);
        $em->flush();
        return $this->redirectToRoute('_affichercl');
    }

}
