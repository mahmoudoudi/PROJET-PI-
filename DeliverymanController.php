<?php

namespace BackBundle\Controller;

use FrontBundle\Entity\Deliveryman;
use FrontBundle\Form\DeliverymanType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DeliverymanController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Back/Default/index.html.twig');
    }

    public function AfficherdeliverymanAction()
    {
        $em = $this->getDoctrine()->getManager();
        $deliveryman = $em->getRepository(Deliveryman::class)->findAll();

        return $this->render('@Back/Deliveryman/DeliverymanBack.html.twig', array(
            'cc' => $deliveryman
            // ...
        ));
    }

    public function DeletedeliverymanAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $deliveryman = $em->getRepository(Deliveryman::class)->find($id);
        $em->remove($deliveryman);
        $em->flush();
        return $this->redirectToRoute('Afficherdeliveryman');
    }

    public function adddeliverymanAction(\Symfony\Component\HttpFoundation\Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $deliveryman = new deliveryman();

        $form = $this->createForm(DeliverymanType::class,$deliveryman);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {

            $deliveryman->setAvailable('non');
            $deliveryman->setScore(0);
            $deliveryman->setNumberscore(0);
            $deliveryman->setPasswordd('hooks');

            $em->persist($deliveryman);
            $em->flush();


            return $this->redirectToRoute('Afficherdeliveryman');
        }
        return $this->render('@Back/Deliveryman/adddeliveryman.html.twig', array(
            'deliveryman' => $deliveryman,
            'form' => $form->createView(),

        ));
    }

    public function editdeliverymanAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $deliveryman = $em->getRepository(Deliveryman::class)->find($id);
        $editForm = $this->createForm(DeliverymanType::class,$deliveryman);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $em->persist($deliveryman);
            $em->flush();

            return $this->redirectToRoute('Afficherdeliveryman');
        }
        $em = $this->getDoctrine()->getManager();

        return $this->render('@Back/Deliveryman/editdeliveryman.html.twig', array(
            'deliveryman' => $deliveryman,
            'form' => $editForm->createView(),
        ));
    }

    public function SearchDelAction(Request $request){
        return $this->render('@Back/Deliveryman/SearchDel.html.twig');

    }
    public function top3Action(Request $request){
        $em = $this->getDoctrine()->getManager();
        $deliveryman = $em->getRepository(Deliveryman::class)->findAll();

        return $this->render('@Back/Deliveryman/top3.html.twig', array(
            'cc' => $deliveryman
            // ...
        ));

    }


}
