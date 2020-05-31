<?php

namespace BackBundle\Controller;


use FrontBundle\Entity\Ratecity;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class RatecityController extends Controller
{
    public function AfficherratecityAction(Request $rq)
    {
        $em = $this->getDoctrine()->getManager();
        $ratecity = $em->getRepository(Ratecity::class)->findAll();

        return $this->render('@Back/Ratecity/ratecity.html.twig', array(
            'cc' => $ratecity
            // ...
        ));
    }

    public function chartcityAction(Request $rq)
    {
        $em = $this->getDoctrine()->getManager();
        $ratecity = $em->getRepository(Ratecity::class)->findAll();

        return $this->render('@Back/Ratecity/chartcity.html.twig', array(
            'cc' => $ratecity
            // ...
        ));
    }

    public function pdfAction(Request $request){
        $snappy=$this->get("knp_snappy.pdf");

        $html=$this->renderView("@Back/Ratecity/rate.html.twig",array(
            "title"=>"Awesome PDF Title"
        ));
        $filename="custom_pdf_from_twig";
        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'Content-Type' => 'application/pdf',
                'Content-Disposition' => 'inline; filename="'.$filename.'.pdf"'
            )
        );
    }

}
