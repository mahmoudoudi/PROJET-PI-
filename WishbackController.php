<?php

namespace BackBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class WishbackController extends Controller
{
    public function showAction()
    {
        $m = $this->getDoctrine()->getManager();
        $User = $m->getRepository("FrontBundle:wishlist")->findAll();
        return $this->render('@Back/wishback/show.html.twig', array(
                'User'=> $User
            )
        );
    }

    public function DeleteAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("FrontBundle:Wishlist")->find($id);
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('show');
    }

}
