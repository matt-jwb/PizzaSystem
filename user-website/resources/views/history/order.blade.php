@extends('layouts.app')

@section('content')
    <div>
        <h2>Order details</h2>
        <p>Order date: {{ $order->date_time }}</p>
        <p>Collection/Delivery: {{ ucfirst($order->collection_delivery) }}</p>
        @if($order->delivery_address != null)
            <p>Delivery address: {{ $order->delivery_address }}</p>
        @endif
        <p>Total Price: £{{ $order->price }}</p>
        @if($order->deal != null)
            <p>Deal: {{ $order->deal }}</p>
        @endif
        @if($order->deal_price != null)
            <p>Total Price after deal: £{{ $order->deal }}</p>
        @endif
        <p></p>
        <h2>Order Contents</h2>
        <table>
            <tr>
                <th>Pizza name</th>
                <th>Ingredients</th>
                <th>Price</th>
            </tr>
            @foreach($ids as $id)
                <tr>
                    <td>{{ $pizzas->where('id', $id)->value('pizza_name') }}</td>
                    <td>{{ $pizzas->where('id', $id)->value('ingredients') }}</td>
                    <td>{{ $pizzas->where('id', $id)->value('price') }}</td>
                </tr>
            @endforeach
        </table>
    </div>
    <br>
    <a href="/orders">Go Back</a>
@endsection
