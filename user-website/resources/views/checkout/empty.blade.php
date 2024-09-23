@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h1>Empty basket</h1>
                    </div>
                    <div class="card-body">
                        <p>There are currently no items in your basket.</p>
                        <p>Pick a pizza from the <a href="/menu">menu</a>!</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
