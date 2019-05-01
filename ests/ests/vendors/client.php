@extends('layouts.adminlte')

@section('content')
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <!--
    <h1>
      Club Login
      <small>Optional description</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
      <li class="active">Here</li>
    </ol>
  </section>
-->
  <!-- Main content -->
  <section class="content container-fluid">
    <div class="row">
      <div class="col-md-12">
        <div class="box box-info pull-right">
                    <div class="box-header with-border">
                      @php
                              $photo=$client->profile->profilePhoto;
                              if(!isset($client->profile->profilePhoto)){
                                $photo='default.jpg';
                              }
                      @endphp
                      <div class="user-block">
            <img class="img-circle img-bordered-sm" src="{{asset('images/'.$photo)}}" alt="user image">
                <span class="username">
                  {{$client->profile->lastname}}, {{$client->profile->firstname}}
                </span>
            <span class="description">
                @if($client->profile_id==$office->profile_id)
                Club administrator
                @else
                Member since {{\Carbon\Carbon::createFromFormat('Y-m-d', $client->joinDate)->format('F d,Y')}}
                @endif
              </span>
          </div>
                      <h3 class="box-title"></h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->
                        <div class="box-body">
                          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
            <li class="active"><a href="#summary" data-toggle="tab">Summary</a></li>
              <li ><a href="#deposits" data-toggle="tab">Deposits</a></li>
              <li><a href="#contributions" data-toggle="tab">Contributions</a></li>
              <li><a href="#loans" data-toggle="tab">Loans</a></li>
              <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  Actions <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Edit</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Print</a></li>
                </ul>
              </li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane active" id="summary">
                         <!---medard start-->
        <div class="row">
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">
               @if(!empty($account_details->clientName))
               {{$account_details->clientName}}({{$account_details->accountNo}})
               @endif
              <p></p><p></p>
              Current Balance:@if(!empty($account_details->summary->accountBalance)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->accountBalance),2)}} @endif
              <p></p>
              <p>Available Balance: @if(!empty($account_details->summary->accountBalance)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->availableBalance),2)}} @endif</p>
              </h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total withdrawals</th>
                  <th>@if(!empty($account_details->summary->totalWithdrawals)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->totalWithdrawals),2)}} @endif</th>
                </tr>
                <!-- <tr>
                 <td>Last Active Transaction Date</td>
                  <td></td>
                </tr>-->
              </table>
            </div>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Performance History</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total Deposits</th>
                  <th> @if(!empty($account_details->summary->totalDeposits)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->totalDeposits),2)}} @endif</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <div class="col-md-12">
          <div class="box">
            <!-- /.box-header -->
            <div class="box-body pull-right">
              <table class="table table-bordered">
                <tr>
                  <th>Deposit</th>
                  <th>Withdraw</th>
                  <th>Transfer Funds</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
           </div>
            <div class="box">
            <div class="box-header">
              <h3 class="box-title">Transactions</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>Transaction Date</th>
                  <th>Transaction Type</th>
                  <th>Debit</th>
                  <th>Credit</th>
                  <th>Balance</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>05/09/2018
                  </td>
                  <td>Deposit</td>
                  <td> 400000</td>
                  <td></td>
                  <td>400000</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>05/09/2018</td>
                  <td>Withdraw</td>
                  <td>150000</td>
                  <td></td>
                  <td>250000</td>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
                <!--medard ends-->
                            
              </div>
              <!-- /.tab-pane -->
              <div class="tab-pane" id="deposits">
                @if(is_null($client->walletaccount))
                {!! Form::open(['route' => ['offices.walletaccounts.store','office'=>$office,'client'=>$client]]) !!}
                <div class="form-group row">
                    <div class="col-md-8">&nbsp;</div>
                    <div class="col-md-2">{{Form::submit('Activate deposits',['class'=>"btn btn-block btn-primary btn-sm"])}}</div>
                </div>
                {!! Form::close() !!}
                @else 
                @if(!is_null($client->walletaccount))
               @php 
               $account_details=CCSL::retrieve_account($client->walletaccount->savingsaccount);
            
               @endphp
              <!---medard start-->
        <div class="row">
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">
               @if(!empty($account_details->clientName))
               {{$account_details->clientName}}({{$account_details->accountNo}})
               @endif
              <p></p><p></p>
              Current Balance:@if(!empty($account_details->summary->accountBalance)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->accountBalance),2)}} @endif
              <p></p>
              <p>Available Balance: @if(!empty($account_details->summary->accountBalance)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->availableBalance),2)}} @endif</p>
              </h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total withdrawals</th>
                  <th>@if(!empty($account_details->summary->totalWithdrawals)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->totalWithdrawals),2)}} @endif</th>
                </tr>
                <!-- <tr>
                 <td>Last Active Transaction Date</td>
                  <td></td>
                </tr>-->
              </table>
            </div>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Performance History</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total Deposits</th>
                  <th> @if(!empty($account_details->summary->totalDeposits)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->totalDeposits),2)}} @endif</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <div class="col-md-12">
          <div class="box">
            <!-- /.box-header -->
            <div class="box-body pull-right">
              <table class="table table-bordered">
                <tr>
                  <th>Deposit</th>
                  <th>Withdraw</th>
                  <th>Transfer Funds</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
           </div>
            <div class="box">
            <div class="box-header">
              <h3 class="box-title">Transactions</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>Transaction Date</th>
                  <th>Transaction Type</th>
                  <th>Debit</th>
                  <th>Credit</th>
                  <th>Balance</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>05/09/2018
                  </td>
                  <td>Deposit</td>
                  <td> 400000</td>
                  <td></td>
                  <td>400000</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>05/09/2018</td>
                  <td>Withdraw</td>
                  <td>150000</td>
                  <td></td>
                  <td>250000</td>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
                <!--medard ends-->
                            
                @endif
                @endif

              </div>
              <!-- /.tab-pane -->
              <div class="tab-pane" id="contributions">
                         <!---medard start-->
        <div class="row">
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">
               @if(!empty($account_details->clientName))
               {{$account_details->clientName}}({{$account_details->accountNo}})
               @endif
              <p></p><p></p>
              Current Balance:@if(!empty($account_details->summary->accountBalance)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->accountBalance),2)}} @endif
              <p></p>
              <p>Available Balance: @if(!empty($account_details->summary->accountBalance)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->availableBalance),2)}} @endif</p>
              </h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total withdrawals</th>
                  <th>@if(!empty($account_details->summary->totalWithdrawals)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->totalWithdrawals),2)}} @endif</th>
                </tr>
                <!-- <tr>
                 <td>Last Active Transaction Date</td>
                  <td></td>
                </tr>-->
              </table>
            </div>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Performance History</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total Deposits</th>
                  <th> @if(!empty($account_details->summary->totalDeposits)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->totalDeposits),2)}} @endif</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <div class="col-md-12">
          <div class="box">
            <!-- /.box-header -->
            <div class="box-body pull-right">
              <table class="table table-bordered">
                <tr>
                  <th>Deposit</th>
                  <th>Withdraw</th>
                  <th>Transfer Funds</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
           </div>
            <div class="box">
            <div class="box-header">
              <h3 class="box-title">Transactions</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>Transaction Date</th>
                  <th>Transaction Type</th>
                  <th>Debit</th>
                  <th>Credit</th>
                  <th>Balance</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>05/09/2018
                  </td>
                  <td>Deposit</td>
                  <td> 400000</td>
                  <td></td>
                  <td>400000</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>05/09/2018</td>
                  <td>Withdraw</td>
                  <td>150000</td>
                  <td></td>
                  <td>250000</td>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
                <!--medard ends-->
                            
              </div>

              <!-- /.tab-pane -->
              <div class="tab-pane" id="loans">
                         <!---medard start-->
        <div class="row">
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">
               @if(!empty($account_details->clientName))
               {{$account_details->clientName}}({{$account_details->accountNo}})
               @endif
              <p></p><p></p>
              Current Balance:@if(!empty($account_details->summary->accountBalance)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->accountBalance),2)}} @endif
              <p></p>
              <p>Available Balance: @if(!empty($account_details->summary->accountBalance)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->availableBalance),2)}} @endif</p>
              </h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total withdrawals</th>
                  <th>@if(!empty($account_details->summary->totalWithdrawals)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->totalWithdrawals),2)}} @endif</th>
                </tr>
                <!-- <tr>
                 <td>Last Active Transaction Date</td>
                  <td></td>
                </tr>-->
              </table>
            </div>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Performance History</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total Deposits</th>
                  <th> @if(!empty($account_details->summary->totalDeposits)){{$account_details->summary->currency->code}} {{number_format(($account_details->summary->totalDeposits),2)}} @endif</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <div class="col-md-12">
          <div class="box">
            <!-- /.box-header -->
            <div class="box-body pull-right">
              <table class="table table-bordered">
                <tr>
                  <th>Deposit</th>
                  <th>Withdraw</th>
                  <th>Transfer Funds</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
           </div>
            <div class="box">
            <div class="box-header">
              <h3 class="box-title">Transactions</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>Transaction Date</th>
                  <th>Transaction Type</th>
                  <th>Debit</th>
                  <th>Credit</th>
                  <th>Balance</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>05/09/2018
                  </td>
                  <td>Deposit</td>
                  <td> 400000</td>
                  <td></td>
                  <td>400000</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>05/09/2018</td>
                  <td>Withdraw</td>
                  <td>150000</td>
                  <td></td>
                  <td>250000</td>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
                <!--medard ends-->
                            
              </div>
              <!-- /.tab-pane -->
              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
                                    </div>
                      <!-- /.box-body -->
                      <div class="box-footer">
                        <div class="row">
                        <div  class="col-sm-10"></div>
                        <div  class="col-sm-2">

                        </div>

                      </div>
                      <!-- /.box-footer -->
          </div>

      </div>
    </div>
  </div>
  </section>
  <!-- /.content -->
@endsection
