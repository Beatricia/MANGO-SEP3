package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.ReviewDaoInterface;
import mango.sep3.databaseaccess.protobuf.Review;
import mango.sep3.databaseaccess.protobuf.ReviewServiceGrpc;
import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class ReviewServiceImpl extends ReviewServiceGrpc.ReviewServiceImplBase {
    @Autowired
    private GrpcConverter grpcConverter;

    @Autowired
    private ReviewDaoInterface reviewDaoInterface;


    @Override
    public void createReview(Review request, StreamObserver<Review> responseObserver) {
        var sharedReview = grpcConverter.convertToShared(request);
        try{
            var savedReview = reviewDaoInterface.saveReview(sharedReview);
            var savedGrpcReview = grpcConverter.convertToGrpc(savedReview);
            responseObserver.onNext(savedGrpcReview);
            responseObserver.onCompleted();

        }catch (Exception e){
            responseObserver.onError(e);
        }
    }
}
