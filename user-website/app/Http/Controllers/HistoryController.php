<?php

namespace App\Http\Controllers;

use Exception;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;

class HistoryController extends Controller
{
    public function index(){
        $orders = DB::table('orders')->get()->where('user_id', Auth::id());
        return view ('history.all', ['orders' => $orders]);
    }

    public function order(int $id){
        $order = DB::table('orders')->get()->where('id', $id)->first();
        $pizzas = DB::table('pizzas')->get();
        $links = DB::table('link_table')->get()->where('order_id', $id);
        $ids = [];

        foreach ($links as $link){
            array_push($ids, $link->pizza_id);
        }

        try{
            if ($order->user_id == Auth::id()){
                return view ('history.order', [
                        'order' => $order,
                        'pizzas' => $pizzas,
                        'ids' => $ids,
                    ]
                );
            }
            else{
                return view ('history.unauthorised');
            }
        }
        catch (Exception){
            return view ('history.error');
        }
    }
}
