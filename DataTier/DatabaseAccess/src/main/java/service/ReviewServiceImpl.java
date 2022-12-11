package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.ReviewDaoInterface;
import mango.sep3.databaseaccess.protobuf.Id64;
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
    private ReviewDaoInterface reviewDao;
    @Autowired
    private FarmDaoInterface farmDao;


    @Override
    public void createReview(Review request, StreamObserver<Review> responseObserver) {
        var sharedReview = grpcConverter.convertToShared(request);
        try{
            var savedReview = reviewDao.saveReview(sharedReview);
            var savedGrpcReview = grpcConverter.convertToGrpc(savedReview);
            responseObserver.onNext(savedGrpcReview);
            responseObserver.onCompleted();

        }catch (Exception e){
            responseObserver.onError(e);
        }
    }

    @Override
    public void editReview(Review request, StreamObserver<Review> responseObserver) {
        var sharedReview = grpcConverter.convertToShared(request);
        var saved = reviewDao.editReview(sharedReview);

        var savedGrpcReview = grpcConverter.convertToGrpc(saved);
        responseObserver.onNext(savedGrpcReview);
        responseObserver.onCompleted();
    }

    @Override
    public void getReviewsByFarm(mango.sep3.databaseaccess.protobuf.Farm request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Reviews> responseObserver) {
        try{
            var farm = farmDao.getFarmByName(request.getName());
            var reviews = reviewDao.getReviewsByFarmAsync(farm);
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

    @Override
    public void getReviewById(Id64 request, StreamObserver<Review> responseObserver) {
        var review = reviewDao.getReviewById(request.getId());
        var savedGrpcReview = grpcConverter.convertToGrpc(review);
        responseObserver.onNext(savedGrpcReview);
        responseObserver.onCompleted();
    }

}
