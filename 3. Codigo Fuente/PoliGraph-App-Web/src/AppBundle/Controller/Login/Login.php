<?php
/**
 * Created by PhpStorm.
 * User: david
 * Date: 26/02/2018
 * Time: 2:50 AM
 */

namespace AppBundle\Controller\Login;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;

class Login extends Controller
{
    /**
     * @Route("/",name="Welcome")
     */
    public function numberAction()
    {
        $number = mt_rand(0, 100);

        return $this->render(
            'Login/BaseLogin.html.twig',
            array('number' => $number)
        );
    }
}