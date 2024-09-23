@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Order</div>

                    <div class="card-body">
                        <?php $totalPrice = 0?>
                        @foreach($basket as $i)
                            <li>{{ $pizzas->where('id', $i)->value('pizza_name') }} - £{{ $price = $pizzas->where('id', $i)->value('price') }}</li>
                            <?php $totalPrice += $price?>
                        @endforeach
                        <br><h4>Total Price: £{{number_format($totalPrice, $decimals = 2)}}</h4><br>
                        <form method="POST" action="/proceed">
                            @csrf
                            <div class="row mb-3">
                                <label for="coldel" class="col-md-4 col-form-label text-md-end">Collection or Delivery?</label>

                                <select name="coldel" id="coldel" class="col-md-6">
                                    <option value="collection">Collection</option>
                                    <option value="delivery">Delivery</option>
                                </select>
                            </div>

                            <div class="row mb-3">
                                <label for="deal" class="col-md-4 col-form-label text-md-end">Deal</label>

                                <select name="deal" id="deal" class="col-md-6">
                                    <option value="none">No deal selected</option>
                                    <option value="bogof">BOGOF</option>
                                    <option value="three_for_two">Three for Two</option>
                                    <option value="family_feast">Family Feast</option>
                                    <option value="two_large">Two Large</option>
                                    <option value="two_medium">Two Medium</option>
                                    <option value="two_small">Two Small</option>
                                </select>
                            </div>
                            <p>Note that only valid deals will be accepted upon checkout. Deal info can be found on the <a href="/menu">menu</a></p>

                            <div class="row mb-0">
                                <div class="col-md-8 offset-md-4">
                                    <button type="submit" class="btn btn-primary">Proceed to Payment</button> <a class="btn btn-primary" href="/basket">Cancel</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
