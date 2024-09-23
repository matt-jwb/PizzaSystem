@extends('layouts.app')

@section('content')
    <div style="text-align: center">
        <hr class="solid">
        <h1>Pizzas</h1>
        <hr class="solid">
        <ul style="list-style-position: inside">
            @if(Session::has('msg'))
                <div class="alert alert-success">
                    <p>{{ Session::get('msg') }}</p>
                </div>
            @endif
            @foreach($pizzas as $i)
                <h3>{{ $i->pizza_name }}</h3>
                <p>Ingredients: {{ $i->ingredients }} <br> Price: £{{ $i->price }} <br> <a class="btn btn-primary" href="/basket/add/{{$i->id}}?redirect=false">Add to order</a></p>
                <br>
            @endforeach
        </ul>
    </div>
    <br><br><br>
    <div style="text-align: center">
        <hr class="solid">
        <h1>Pizza Deals</h1>
        <hr class="solid">
        <h3>BOGOF</h3>
        <ul style="list-style-position: inside">
            <li>Two medium or two large pizzas</li>
            <li>Charged at the price of the highest priced pizza selected</li>
            <li>Collection/delivery</li>
        </ul>
        <h3>Three for two</h3>
        <ul style="list-style-position: inside">
            <li>Three medium pizzas</li>
            <li>Charged at the price of the two highest priced pizzas selected</li>
            <li>Collection/delivery</li>
        </ul>
        <h3>Family Feast</h3>
        <ul style="list-style-position: inside">
            <li>Four medium pizzas</li>
            <li>Price £30</li>
            <li>Collection only</li>
        </ul>
        <h3>Two large</h3>
        <ul style="list-style-position: inside">
            <li>Two large pizzas</li>
            <li>Price £25</li>
            <li>Collection only</li>
        </ul>
        <h3>Two medium</h3>
        <ul style="list-style-position: inside">
            <li>Two medium pizzas</li>
            <li>Price £18</li>
            <li>Collection only</li>
        </ul>
        <h3>Two small</h3>
        <ul style="list-style-position: inside">
            <li>Two small pizzas</li>
            <li>Price £12</li>
            <li>Collection only</li>
        </ul>
    </div>
@endsection
