<?php

namespace BackBundle\Controller;


use FrontBundle\Form\ProductType;
use FrontBundle\Entity\Category;
use FrontBundle\Entity\Product;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class ProductController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Back/Default/index.html.twig');
    }

    public function addproductAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $product = new product();
        $category=$em->getRepository( Category::class)->findAll();
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $product->setNamecategory($request->get('category'));
            $product->setPromotion(0);
            $em->persist($product);
            $em->flush();


            return $this->redirectToRoute('Afficherproduct');
        }
        return $this->render('@Back/Product/addproduct.html.twig', array(
            'product' => $product,'cc'=>$category,
            'form' => $form->createView(),

        ));
    }
    public function editproductAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $product = $em->getRepository(Product::class)->find($id);
        $editForm = $this->createForm(ProductType::class, $product);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $em->persist($product);
            $em->flush();

            return $this->redirectToRoute('Afficherproduct');
        }
        $em = $this->getDoctrine()->getManager();

        return $this->render('@Back/Product/editproduct.html.twig', array(
            'product' => $product,
            'form' => $editForm->createView(),
        ));
    }
    public function DeleteproductAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository(Product::class)->find($id);
        $em->remove($product);
        $em->flush();
        return $this->redirectToRoute('Afficherproduct');
    }
    public function AfficherproductAction()
    {
        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository(Product::class)->findAll();

        return $this->render('@Back/Product/ProductBack.html.twig', array(
            'hatem' => $product
            // ...
        ));

    }
}
