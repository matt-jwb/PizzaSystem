@extends('layouts.app')

@section('content')
    <div>
        <h1>{{ Auth::user()->name }}'s Orders</h1>
        <ul>
            @foreach($orders as $i)
                <li>{{ $i->pizzas }}</li>
                <ul>
                    @if($i->deal_price != null)
                        <li>£{{ $i->deal_price }}</li>
                    @else
                        <li>£{{ $i->price }}</li>
                    @endif
                    <li><a href="orders/{{$i->id}}">More info</a></li>
                </ul>
            @endforeach
        </ul>
    </div>
@endsection
