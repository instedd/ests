@extends('layouts.adminlte')

@section('content')
@php
        $photo=$profile->profilePhoto;
        if(!isset($profile->profilePhoto)){
          $photo='default.jpg';
        }
@endphp
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <!--
    <section class="content-header">
      <h1>
        User Profile
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Examples</a></li>
        <li class="active">User profile</li>
      </ol>
    </section>
-->
    <!-- Main content -->
    <section class="content">

      <div class="row">
        <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="{{asset('images/'.$photo)}}" alt="User profile picture">

              <h3 class="profile-username text-center">Welcome {{$profile->firstname}} {{$profile->lastname}}!</h3>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  Mobile number: <span>{{$profile->phonenumber}}</span>
                </li>
                <li class="list-group-item">
                Email: <span>{{$profile->user->email}}</span>
                </li>
              </ul>

              <a href="{!! route("profiles.edit", ["profile" => $profile]) !!}" class="btn btn-primary btn-block"><b>Edit profile</b></a>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          <!-- About Me Box -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">&nbsp;</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        <div class="col-md-9">
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
            <li class="active"><a href="#recent-activity" data-toggle="tab">Recent activity</a></li>
              <li ><a href="#clubs" data-toggle="tab">My clubs</a></li>
              <li ><a href="#wallet" data-toggle="tab">My Wallet</a></li>
            </ul>
            <div class="tab-content">
              <!-- /.tab-pane -->
              <div class="tab-pane  active" id="recent-activity">
                <!-- The timeline -->
                <ul class="timeline timeline-inverse">
                  <!-- timeline time label -->
                  <li class="time-label">
                        <span class="bg-red">
                          10 Feb. 2014
                        </span>
                  </li>
                  <!-- /.timeline-label -->
                  <!-- timeline item -->
                  <li>
                    <i class="fa fa-envelope bg-blue"></i>

                    <div class="timeline-item">
                      <span class="time"><i class="fa fa-clock-o"></i> 12:05</span>

                      <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>

                      <div class="timeline-body">
                        Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                        weebly ning heekya handango imeem plugg dopplr jibjab, movity
                        jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                        quora plaxo ideeli hulu weebly balihoo...
                      </div>
                      <div class="timeline-footer">
                        <a class="btn btn-primary btn-xs">Read more</a>
                        <a class="btn btn-danger btn-xs">Delete</a>
                      </div>
                    </div>
                  </li>
                  <!-- END timeline item -->
                  <!-- timeline item -->
                  <li>
                    <i class="fa fa-user bg-aqua"></i>

                    <div class="timeline-item">
                      <span class="time"><i class="fa fa-clock-o"></i> 5 mins ago</span>

                      <h3 class="timeline-header no-border"><a href="#">Sarah Young</a> accepted your friend request
                      </h3>
                    </div>
                  </li>
                  <!-- END timeline item -->
                  <!-- timeline item -->
                  <li>
                    <i class="fa fa-comments bg-yellow"></i>

                    <div class="timeline-item">
                      <span class="time"><i class="fa fa-clock-o"></i> 27 mins ago</span>

                      <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                      <div class="timeline-body">
                        Take me to your leader!
                        Switzerland is small and neutral!
                        We are more like Germany, ambitious and misunderstood!
                      </div>
                      <div class="timeline-footer">
                        <a class="btn btn-warning btn-flat btn-xs">View comment</a>
                      </div>
                    </div>
                  </li>
                  <!-- END timeline item -->
                  <!-- timeline time label -->
                  <li class="time-label">
                        <span class="bg-green">
                          3 Jan. 2014
                        </span>
                  </li>
                  <!-- /.timeline-label -->
                  <!-- timeline item -->
                  <li>
                    <i class="fa fa-camera bg-purple"></i>

                    <div class="timeline-item">
                      <span class="time"><i class="fa fa-clock-o"></i> 2 days ago</span>

                      <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                      <div class="timeline-body">
                        <img src="http://placehold.it/150x100" alt="..." class="margin">
                        <img src="http://placehold.it/150x100" alt="..." class="margin">
                        <img src="http://placehold.it/150x100" alt="..." class="margin">
                        <img src="http://placehold.it/150x100" alt="..." class="margin">
                      </div>
                    </div>
                  </li>
                  <!-- END timeline item -->
                  <li>
                    <i class="fa fa-clock-o bg-gray"></i>
                  </li>
                </ul>
              </div>
              <!-- /.tab-pane -->

                <div class="tab-pane" id="wallet">
                  @php
                  $root_office=App\Office::where('name','Cinnamon Clubs')->first();
                  $root_client=App\Client::where('profile_id',$profile->id)->first();
                  @endphp
                  @if(is_null($root_client->walletaccount))
                  {!! Form::open(['route' => ['offices.walletaccounts.store','office'=>$office,'client'=>$root_client]]) !!}
                  <div class="form-group row">
                      <div class="col-md-8">&nbsp;</div>
                      <div class="col-md-2">{{Form::submit('Activate deposits',['class'=>"btn btn-block btn-primary btn-sm"])}}</div>
                  </div>
                  {!! Form::close() !!}
                  @else
                  @php
                  $account_details=CCSL::retrieve_account($root_client->walletaccount->savingsaccount);
                @endphp  
                     <!---medard start-->
        <div class="row">
        <div class="col-md-6">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">
              @if(!empty($account_details->pageItems[0]->clientName))
              {{$account_details->pageItems[0]->clientName}}({{$account_details->pageItems[0]->accountNo}})
              @endif
              <p></p>
              @if(!empty($account_details->pageItems[0]->savingsProductName))
              Saving Product Name:{{$account_details->pageItems[0]->savingsProductName}}
              @endif
              <p></p>
              Current Balance:@if(!empty($account_details->pageItems[0]->summary->accountBalance))
              {{$account_details->pageItems[0]->summary->currency->code}} {{number_format(($account_details->pageItems[0]->summary->accountBalance),2)}}
              @endif
              <p></p>
              <p>
                Available Balance:@if(!empty($account_details->pageItems[0]->summary->availableBalance))
                {{$account_details->pageItems[0]->summary->currency->code}} {{number_format(($account_details->pageItems[0]->summary->availableBalance),2)}}
                 @endif
              </p>
              </h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Total withdrawals</th>
                  <th>@if(!empty($account_details->pageItems[0]->summary->totalWithdrawals))
                       {{$account_details->pageItems[0]->summary->currency->code}} {{number_format(($account_details->pageItems[0]->summary->totalWithdrawals),2)}}
                     @endif
                   </th>
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
                  <th> @if(!empty($account_details->pageItems[0]->summary->totalDeposits))
                       {{$account_details->pageItems[0]->summary->currency->code}} {{number_format(($account_details->pageItems[0]->summary->totalDeposits),2)}}
                     @endif</th>
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
                  <td></td>
                  <td>
                  </td>
                  <td></td>
                  <td> </td>
                  <td></td>
                  <td></td>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
                <!--medard ends-->
                    

                  @endif
                </div>


                  <div class="tab-pane" id="clubs">

                    <table class="table table-hover">
                      @foreach ($offices as $office)
                          @if($office->name!='Cinnamon Clubs')
                          <tr><td><a href='{!! route("offices.show", ["office" => $office]) !!}'>{{$office->name}}</a></td></tr>
                          @endif
                      @endforeach
                        <tr><td><a href="{{ route('offices.create')}}" class="btn btn-block btn-primary btn-sm" style="width:20%"> Create a new club</a></td></tr>
                  </table>
                  </div>
              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

@endsection
