<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Session;

class OrderController extends Controller
{
    public function index(){
        $pizzas = DB::table('pizzas')->get();
        $basket = Session::get('basket');
        return view('pizza.basket', [
                'basket' => $basket,
                'pizzas' => $pizzas
            ]
        );
    }

    public function add(int $id, Request $request){
        Session::push('basket', $id);
        $redirect = $request->query('redirect');
        if ($redirect == 'false'){
            return redirect ('/menu')->with('msg', 'Pizza added to order');
        }
        else{
            return redirect('/basket');
        }
    }

    public function remove(int $id){
        $basket = Session::get('basket');
        unset($basket[$id]);
        Session::remove('basket');
        foreach ($basket as $i){
            Session::push('basket', $i);
        }
        return redirect('/basket');
    }

    public function removeAll(){
        Session::remove('basket');
        return redirect('/basket');
    }
}
