<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class MenuController extends Controller
{
    public function index(){
        $pizzas = DB::table('pizzas')->get();
        return view ('pizza.menu', ['pizzas' => $pizzas]);
    }
}
