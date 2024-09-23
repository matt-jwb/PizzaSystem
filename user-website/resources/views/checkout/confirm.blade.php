@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header" style="text-align: center">
                        <h1>Confirm</h1>
                    </div>
                    <div class="card-body">
                        <?php $totalPrice = 0?>
                        @foreach($basket as $i)
                            <li>{{ $pizzas->where('id', $i)->value('pizza_name') }} - £{{ $price = $pizzas->where('id', $i)->value('price') }}</li>
                                <?php $totalPrice += $price?>
                        @endforeach
                        @if($request->deal != "none")
                            <p>Deal selected: {{ $request->deal }}</p>
                            <br><h5>Original Price: £{{number_format($totalPrice, $decimals = 2)}}</h5>
                            <h4>Price after deal: £{{ $newPrice }}</h4><br>
                        @else
                            <br><h4>Total Price: £{{number_format($totalPrice, $decimals = 2)}}</h4><br>
                        @endif
                        <p>Collection/Delivery: {{ $request->coldel }}</p>
                        <form method="POST" action="/confirm">
                            @csrf
                            <div class="row mb-0">
                                <div class="col-md-8 offset-md-4">
                                    <button type="submit" class="btn btn-primary">Place order</button> <a class="btn btn-primary" href="/basket">Cancel</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
