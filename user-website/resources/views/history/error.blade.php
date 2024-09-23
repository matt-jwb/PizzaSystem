@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header" style="text-align: center">
                        <h1>Error</h1>
                    </div>
                    <div class="card-body">
                        <p>The order you are looking for does not exist</p>
                        <a href="/orders">Go Back</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
