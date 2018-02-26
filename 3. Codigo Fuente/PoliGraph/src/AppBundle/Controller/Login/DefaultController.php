<?php

namespace AppBundle\Controller\Login;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    /**
     * @Route("/", name="Login")
     */
    public function indexAction(Request $request)
    {
        // replace this example code with whatever you need
        return $this->render('BaseLogin.html.twig',array());
    }
}
