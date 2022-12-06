package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.ReviewDaoInterface;
import mango.sep3.databaseaccess.protobuf.Review;
import mango.sep3.databaseaccess.protobuf.ReviewServiceGrpc;
import mango.sep3.databaseaccess.protobuf.Reviews;
import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@GRpcService
public class ReviewServiceImpl extends ReviewServiceGrpc.ReviewServiceImplBase {
    @Autowired
    private GrpcConverter grpcConverter;

    @Autowired
    private ReviewDaoInterface reviewDaoInterface;
    @Autowired
    private FarmDaoInterface farmDaoInterface;


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

    @Override
    public void getReviewsByFarm(mango.sep3.databaseaccess.protobuf.Farm request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Reviews> responseObserver) {
        try{
            var farm = farmDaoInterface.getFarmByName(request.getName());
            var reviews = reviewDaoInterface.getReviewsByFarmAsync(farm);
            Reviews.Builder response = Reviews.newBuilder();

            Collection<Review> protoReviews = new ArrayList<>();

            for (mango.sep3.databaseaccess.Shared.Review e: reviews){
                var savedGrpcReview = grpcConverter.convertToGrpc(e);
                protoReviews.add(savedGrpcReview);
            }
            response.addAllReviews(protoReviews);

            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        }catch (Exception e){
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

}
