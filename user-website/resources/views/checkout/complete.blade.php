@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h1>Order complete</h1>
                    </div>
                    <div class="card-body">
                        <p>Your order has been added to our system!</p>
                        <p>You can view your previous orders <a href="/orders">here</a>!</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
