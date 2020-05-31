<?php

namespace BackBundle\Controller;


use FrontBundle\Entity\Category;
use FrontBundle\Form\CategoryType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class CategoryController extends Controller
{
    public function editcategoryAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $category = $em->getRepository(Category::class)->find($id);
        $editForm = $this->createForm(CategoryType::class, $category);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $em->persist($category);
            $em->flush();

            return $this->redirectToRoute('Affichercategory');
        }
        $em = $this->getDoctrine()->getManager();

        return $this->render('@Back/Category/editcategory.html.twig', array(
            'category' => $category,
            'form' => $editForm->createView(),
        ));
    }
    public function AffichercategoryAction()
    {
        $em = $this->getDoctrine()->getManager();
        $category = $em->getRepository(Category::class)->findAll();

        return $this->render('@Back/Category/CategoryBack.html.twig', array(
            'cc' => $category
            // ...
        ));
    }

    public function DeletecategoryAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $category = $em->getRepository(Category::class)->find($id);
        $em->remove($category);
        $em->flush();
        return $this->redirectToRoute('Affichercategory');
    }
    public function addcategoryAction(\Symfony\Component\HttpFoundation\Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $category = new category();
        $form = $this->createForm(CategoryType::class, $category);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {

            $em->persist($category);
            $em->flush();

            return $this->redirectToRoute('Affichercategory');
        }
        return $this->render('@Back/Category/addcategory.html.twig', array(
            'category' => $category,
            'f' => $form->createView(),

        ));
    }
}
