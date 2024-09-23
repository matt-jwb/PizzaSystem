<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Session;

class CheckoutController extends Controller
{
    public function index(){
        $pizzas = DB::table('pizzas')->get();
        $basket = Session::get('basket');

        if ($basket == null){
            return view ('checkout.empty');
        }

        return view('checkout.checkout', [
                'basket' => $basket,
                'pizzas' => $pizzas
            ]
        );
    }

    public function proceed(Request $request){
        $pizzas = DB::table('pizzas')->get();
        $basket = Session::get('basket');
        $msgs = [];
        Session::remove('coldel');
        Session::put('coldel', $request->coldel);
        Session::remove('deal');
        Session::put('deal', $request->deal);
        if($request->deal == "bogof"){
            if(sizeof($basket) != 2){
                array_push($msgs, "BOGOF must have 2 pizzas selected");
            }
            else{
                if (str_contains($pizzas->where('id', $basket[0])->value('pizza_name'), 'Medium') && str_contains($pizzas->where('id', $basket[1])->value('pizza_name'), 'Medium') || str_contains($pizzas->where('id', $basket[0])->value('pizza_name'), 'Large') && str_contains($pizzas->where('id', $basket[1])->value('pizza_name'), 'Large')){
                    $newPrice = max($pizzas->where('id', $basket[0])->value('price'), $pizzas->where('id', $basket[1])->value('price'));
                    Session::remove('dealPrice');
                    Session::put('dealPrice', $newPrice);
                    return view('checkout.confirm', [
                            'basket' => $basket,
                            'pizzas' => $pizzas,
                            'request' => $request,
                            'newPrice' => $newPrice
                        ]
                    );
                }
                else{
                    array_push($msgs, "BOGOF must be 2 small or 2 medium");
                }
            }
            return view('checkout.error', ['msgs' => $msgs]);
        }
        elseif($request->deal == "three_for_two"){
            if(sizeof($basket) != 3){
                array_push($msgs, "Three for Two requires three pizzas");
            }
            else{
                if (str_contains($pizzas->where('id', $basket[0])->value('pizza_name'), 'Medium') && str_contains($pizzas->where('id', $basket[1])->value('pizza_name'), 'Medium') && str_contains($pizzas->where('id', $basket[2])->value('pizza_name'), 'Medium')){
                    $prices = [$pizzas->where('id', $basket[0])->value('price'), $pizzas->where('id', $basket[1])->value('price'), $pizzas->where('id', $basket[2])->value('price')];
                    sort($prices);
                    $newPrice = $prices[1] + $prices[2];
                    Session::remove('dealPrice');
                    Session::put('dealPrice', $newPrice);
                    return view('checkout.confirm', [
                            'basket' => $basket,
                            'pizzas' => $pizzas,
                            'request' => $request,
                            'newPrice' => $newPrice
                        ]
                    );
                }
                else{
                    array_push($msgs, "Three for Two pizzas must all be medium");
                }
            }
            return view('checkout.error', ['msgs' => $msgs]);
        }
        elseif($request->deal == "family_feast"){
            if(sizeof($basket) != 4){
                array_push($msgs, "Family Feast requires four pizzas");
            }
            else{
                if (str_contains($pizzas->where('id', $basket[0])->value('pizza_name'), 'Medium') && str_contains($pizzas->where('id', $basket[1])->value('pizza_name'), 'Medium') && str_contains($pizzas->where('id', $basket[2])->value('pizza_name'), 'Medium') && str_contains($pizzas->where('id', $basket[3])->value('pizza_name'), 'Medium')){
                    if($request->coldel == "collection"){
                        $newPrice = 30;
                        Session::remove('dealPrice');
                        Session::put('dealPrice', $newPrice);
                        return view('checkout.confirm', [
                                'basket' => $basket,
                                'pizzas' => $pizzas,
                                'request' => $request,
                                'newPrice' => $newPrice
                            ]
                        );
                    }
                    else{
                        array_push($msgs, "Three for Two pizzas are only available for collection");
                    }
                }
                else{
                    array_push($msgs, "Three for Two pizzas must all be medium");
                }
            }
            return view('checkout.error', ['msgs' => $msgs]);
        }
        elseif($request->deal == "two_large"){
            if(sizeof($basket) != 2){
                array_push($msgs, "Two large must be 2 pizzas");
            }
            else{
                if (str_contains($pizzas->where('id', $basket[0])->value('pizza_name'), 'Large') && str_contains($pizzas->where('id', $basket[1])->value('pizza_name'), 'Large')){
                    if($request->coldel == "collection"){
                        $newPrice = 25;
                        Session::remove('dealPrice');
                        Session::put('dealPrice', $newPrice);
                        return view('checkout.confirm', [
                                'basket' => $basket,
                                'pizzas' => $pizzas,
                                'request' => $request,
                                'newPrice' => $newPrice
                            ]
                        );
                    }
                    else{
                        array_push($msgs, "Two large deals are only available for collection");
                    }
                }
                else{
                    array_push($msgs, "This deal requires pizzas to be Large");
                }
            }
            return view('checkout.error', ['msgs' => $msgs]);
        }
        elseif($request->deal == "two_medium"){
            if(sizeof($basket) != 2){
                array_push($msgs, "Two medium must be 2 pizzas");
            }
            else{
                if (str_contains($pizzas->where('id', $basket[0])->value('pizza_name'), 'Medium') && str_contains($pizzas->where('id', $basket[1])->value('pizza_name'), 'Medium')){
                    if($request->coldel == "collection"){
                        $newPrice = 18;
                        Session::remove('dealPrice');
                        Session::put('dealPrice', $newPrice);
                        return view('checkout.confirm', [
                                'basket' => $basket,
                                'pizzas' => $pizzas,
                                'request' => $request,
                                'newPrice' => $newPrice
                            ]
                        );
                    }
                    else{
                        array_push($msgs, "Two medium deals are only available for collection");
                    }
                }
                else{
                    array_push($msgs, "This deal requires pizzas to be Medium");
                }
            }
            return view('checkout.error', ['msgs' => $msgs]);
        }
        elseif($request->deal == "two_small"){
            if(sizeof($basket) != 2){
                array_push($msgs, "Two small must be 2 pizzas");
            }
            else{
                if (str_contains($pizzas->where('id', $basket[0])->value('pizza_name'), 'Small') && str_contains($pizzas->where('id', $basket[1])->value('pizza_name'), 'Small')){
                    if($request->coldel == "collection"){
                        $newPrice = 12;
                        Session::remove('dealPrice');
                        Session::put('dealPrice', $newPrice);
                        return view('checkout.confirm', [
                                'basket' => $basket,
                                'pizzas' => $pizzas,
                                'request' => $request,
                                'newPrice' => $newPrice
                            ]
                        );
                    }
                    else{
                        array_push($msgs, "Two small deals are only available for collection");
                    }
                }
                else{
                    array_push($msgs, "This deal requires pizzas to be Small");
                }
            }
            return view('checkout.error', ['msgs' => $msgs]);
        }
        // If no deals are selected
        $newPrice = 0; //Not needed so just set to 0
        return view('checkout.confirm', [
                'basket' => $basket,
                'pizzas' => $pizzas,
                'request' => $request,
                'newPrice' => $newPrice
            ]
        );
    }

    public function confirm(){
        $pizzas = DB::table('pizzas')->get();
        $deal = Session::get('deal');
        $dealPrice = Session::get('dealPrice');
        $coldel = Session::get('coldel');
        $basket = Session::get('basket');
        Session::remove('deal');
        Session::remove('dealPrice');
        Session::remove('coldel');
        Session::remove('basket');

        $price = 0;
        $pizzaNames = "";
        foreach ($basket as $i){
            $price += $pizzas->where('id', $i)->value('price');
            $pizzaNames = $pizzaNames . $pizzas->where('id', $i)->value('pizza_name') . ", ";
        }
        $pizzaNames = rtrim($pizzaNames);

        if ($deal == "none"){
            $deal = null;
        }

        DB::insert('insert into orders (user_id, date_time, collection_delivery, delivery_address, price, pizzas, deal, deal_price) values (?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?)', [Auth::id(), $coldel, 'Address', $price, $pizzaNames, $deal, $dealPrice]);

        foreach($basket as $i){
            $orderID = DB::table('orders')->where('id', DB::raw("(select max(`id`) from orders)"))->first();
            DB::insert('insert into link_table (order_id, pizza_id) values (?, ?)', [$orderID->id, $i]);
        }
        return view('checkout.complete');
    }
}
