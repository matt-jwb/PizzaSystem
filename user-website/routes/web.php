<?php

use App\Http\Controllers\CheckoutController;
use App\Http\Controllers\MenuController;
use App\Http\Controllers\HistoryController;
use App\Http\Controllers\OrderController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');

Route::get('/menu', [MenuController::class, 'index'])->middleware('auth');

Route::get('/orders', [HistoryController::class, 'index'])->middleware('auth');

Route::get('/orders/{id}', [HistoryController::class, 'order'])->middleware('auth');

Route::get('/basket', [OrderController::class, 'index'])->middleware('auth');

Route::get('/basket/add/{id}', [OrderController::class, 'add'])->middleware('auth');

Route::get('/basket/remove/{id}', [OrderController::class, 'remove'])->middleware('auth');

Route::get('/basket/removeall', [OrderController::class, 'removeAll'])->middleware('auth');

Route::get('/checkout', [CheckoutController::class, 'index'])->middleware('auth');
Route::post('/proceed', [CheckoutController::class, 'proceed'])->middleware('auth');
Route::post('/confirm', [CheckoutController::class, 'confirm'])->middleware('auth');
