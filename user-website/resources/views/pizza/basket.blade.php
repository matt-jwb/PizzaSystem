@extends('layouts.app')

@section('content')
    <div>
        <ol>
            @if($basket != null)
                <h3>Your basket:</h3>
                <?php $counter = 0; $totalPrice = 0?>
                @foreach($basket as $i)
                    <li>{{ $pizzas->where('id', $i)->value('pizza_name') }}</li>
                    <ul>
                        <li>£{{ $price = $pizzas->where('id', $i)->value('price') }}</li>
                        <li><a href="basket/remove/{{$counter}}">Remove from order</a></li>
                    </ul>
                    <?php $counter++; $totalPrice += $price?>
                @endforeach
                <br>
                <p>Total Price: £{{number_format($totalPrice, $decimals = 2)}}</p>
                <a class="btn btn-primary" href="/checkout">Checkout</a> <a class="btn btn-primary" href="basket/removeall">Empty Basket</a>
            @else
                <h3>Your basket is empty</h3>
            @endif
        </ol>
    </div>
@endsection
