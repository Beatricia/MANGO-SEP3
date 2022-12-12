package service;

import mango.sep3.databaseaccess.DAOInterfaces.AdminDaoInterface;
import mango.sep3.databaseaccess.Shared.Report;
//import mango.sep3.databaseaccess.protobuf.AdminServiceGrpc;
import mango.sep3.databaseaccess.protobuf.ReportServiceGrpc;
import mango.sep3.databaseaccess.protobuf.Reports;
import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@GRpcService
public class ReportServiceImpl extends ReportServiceGrpc.ReportServiceImplBase
{
  @Autowired private AdminDaoInterface adminDaoInterface;
  @Autowired private GrpcConverter grpcConverter;

  public ReportServiceImpl(){

  }

  @Override
  public void getReports(mango.sep3.databaseaccess.protobuf.Void request,
      io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Reports> responseObserver) {

   Collection<Report> reportsShared = adminDaoInterface.GetReports();

   Collection<mango.sep3.databaseaccess.protobuf.Report> reportsProto = new ArrayList<>();

   for(var report: reportsShared){
     mango.sep3.databaseaccess.protobuf.Report reportToSend = grpcConverter.convertToGrpc(report);
     reportsProto.add(reportToSend);
   }

    Reports response = Reports.newBuilder().addAllReports(reportsProto)
        .build();

   responseObserver.onNext(response);
   responseObserver.onCompleted();
  }

}
