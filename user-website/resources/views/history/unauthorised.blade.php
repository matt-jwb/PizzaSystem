@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h1>Unauthorised</h1>
                    </div>
                    <div class="card-body">
                        <p>The order you are looking for belongs to another user</p>
                        <a href="/orders">Go Back</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
