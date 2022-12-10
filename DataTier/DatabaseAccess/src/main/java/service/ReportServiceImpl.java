package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.ReportDaoInterface;
import mango.sep3.databaseaccess.Shared.Report;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@GRpcService
public class ReportServiceImpl extends ReportServiceGrpc.ReportServiceImplBase
{
  @Autowired private ReportDaoInterface adminDaoInterface;
  @Autowired private GrpcConverter grpcConverter;

  public ReportServiceImpl(){

  }

  @Override
  public void getReports(mango.sep3.databaseaccess.protobuf.Void request,
      io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Reports> responseObserver) {

   Collection<Report> reportsShared = adminDaoInterface.getReports();

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

  @Override public void deleteReport(Id64 request,
      StreamObserver<Void> responseObserver)
  {
    adminDaoInterface.deleteReport(request.getId());
    Void response = Void.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

    @Override
    public void createReport(mango.sep3.databaseaccess.protobuf.Report request, StreamObserver<mango.sep3.databaseaccess.protobuf.Report> responseObserver) {
        var sharedReport = grpcConverter.convertToShared(request);
    }
}
