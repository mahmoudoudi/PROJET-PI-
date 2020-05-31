<?php

namespace BackBundle\Controller;

use FrontBundle\Entity\Delivery;
use FrontBundle\Entity\Deliveryman;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;



class DeliveryController extends Controller
{
    public function AfficherdeliveryAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $delivery = $em->getRepository(Delivery::class)->findAll();

        return $this->render('@Back/Delivery/DeliveryBack.html.twig', array(
            'cc'=>$delivery,
            // ...
        ));
    }
    public function affecterdeliveryAction(Request $request,$id)
    {
        $em = $this->getDoctrine()->getManager();
        $delivery=$em->getRepository(Delivery::class)->find($id);
        $deliveryman=$em->getRepository( Deliveryman::class)->findAll();



        return $this->render('@Back/Delivery/affecterdelivery.html.twig',array(
            'cc'=>$deliveryman,'c'=>$delivery
            )
            // ...
        );

    }

    public function chooseAction(Request $request,$id){

        $em = $this->getDoctrine()->getManager();
        $deliveryman = $em->find(Deliveryman::class,$id);
        $delivery=$em->find(Delivery::class,$request->get('ide'));

        $score=$request->get('s');
        $deliveryman->setScore($score+10);
        $deliveryman->setAvailable('non');
        $delivery->setEtatd(1);

            $em->flush();




        return $this->redirectToRoute('Afficherdelivery');

    }



}
