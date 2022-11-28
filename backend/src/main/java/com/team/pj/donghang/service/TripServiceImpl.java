package com.team.pj.donghang.service;

import com.team.pj.donghang.api.request.TripCreateRequestDto;
import com.team.pj.donghang.api.request.TripUpdateRequestDto;
import com.team.pj.donghang.api.response.LastTripResponseDto;
import com.team.pj.donghang.api.response.TripResponseDto;
import com.team.pj.donghang.domain.dto.*;
import com.team.pj.donghang.domain.entity.*;
import com.team.pj.donghang.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class TripServiceImpl implements TripService {
    @Autowired
     TripRepository  tripRepository;

    @Autowired
    TripPlaceRepository tripPlaceRepository;

    @Autowired
    ShoppingRepository shoppingRepository;

    @Autowired
    CultureRepository cultureRepository;

    @Autowired
    LeisureRepository leisureRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FestivalRepository festivalRepository;

    @Autowired
    TouristRepository touristRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlaceCommonRepository placeCommonRepository;

    @Autowired
    PhotoRepository photoRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public List<?> getPlaceDetail(Long commonNo, String category) {

        if (category.equals("shopping")) {
            List<ShoppingDetailDto> list = new ArrayList<>();
            if(shoppingRepository.existsById(commonNo)) {
                list.add(getShoppingDetail(commonNo));
            }
            return list;
        } else if (category.equals("culture")) {
            List<CultureDetailDto> list = new ArrayList<>();
            if(cultureRepository.existsById(commonNo)) {
                list.add(getCultureDetail(commonNo));
            }
            return list;
        } else if (category.equals("festival")) {
            List<FestivalDetailDto> list = new ArrayList<>();
            if(festivalRepository.existsById(commonNo)) {
                list.add(getFestivalDetail(commonNo));
            }
            return list;
        } else if (category.equals("restaurant")) {
            List <RestaurantDetailDto> list = new ArrayList<>();
            if(restaurantRepository.existsById(commonNo)) {
                list.add(getRestaurantDetail(commonNo));
            }
            return list;
        } else if (category.equals("tourist")) {
            List<TouristSpotDetailDto> list = new ArrayList<>();
            if(touristRepository.existsById(commonNo)) {
                list.add(getTourSpotDetail(commonNo));
            }
            return list;
        } else {
            List<LeisureDetailDto> list = new ArrayList<>();
            if(leisureRepository.existsById(commonNo)) {
                list.add(getLeisureDetail(commonNo));
            }
            return list;
        }


    }

    @Transactional
    @Override
    public void createTrip(User user, TripCreateRequestDto tripCreateRequestDto) {
        Trip trip = Trip.builder().tripName(tripCreateRequestDto.getTripName())
                .startDate(tripCreateRequestDto.getStartDate())
                .user(user)
                .endDate(tripCreateRequestDto.getEndDate()).build();
        Long num = tripRepository.save(trip).getTripNo();
        List<TripPlace> tripPlaceList = new ArrayList<>();
        Long[] commonNoList = tripCreateRequestDto.getCommonNoList();
        for (Long commonNo:commonNoList) {
            PlaceCommon common = placeCommonRepository.findByCommonNo(commonNo);

            TripPlace tripPlace = TripPlace.builder()
                    .trip(trip)
                    .common(common)
                    .build();
            tripPlaceList.add(tripPlace);
        }
        tripPlaceRepository.saveAll(tripPlaceList);
    }
    @Transactional
    @Override
    public boolean deleteTrip(User user, Long tripNo) {
        Trip trip = tripRepository.findByTripNo(tripNo);
        if(trip==null){
            return false;
        }

        if(!Objects.equals(user.getUserNo(), trip.getUser().getUserNo())) {
            return false;
        }


        tripPlaceRepository.deleteByTrip_TripNo(tripNo);
        tripRepository.delete(trip);
        return true;
    }

    @Transactional
    @Override
    public boolean updateTrip(User user, TripUpdateRequestDto tripUpdateRequestDto) {

        Trip trip = tripRepository.findByTripNo(tripUpdateRequestDto.getTripNo());

        if(trip==null){
            return false;
        }
        if(user.getUserNo()!= trip.getUser().getUserNo()) {
            return false;
        }


        trip.setTripName(tripUpdateRequestDto.getTripName());
        trip.setEndDate(tripUpdateRequestDto.getEndDate());
        trip.setStartDate(tripUpdateRequestDto.getStartDate());

        Long num = tripRepository.save(trip).getTripNo();
        tripPlaceRepository.deleteByTrip_TripNo(num);

        List<TripPlace> tripPlaceList = new ArrayList<>();
        Long[] commonNoList = tripUpdateRequestDto.getCommonNoList();
        for (Long commonNo:commonNoList) {
            PlaceCommon common = placeCommonRepository.findByCommonNo(commonNo);

            TripPlace tripPlace = TripPlace.builder()
                    .trip(trip)
                    .common(common)
                    .build();
            tripPlaceList.add(tripPlace);
        }
        tripPlaceRepository.saveAll(tripPlaceList);
        return true;
    }

    @Override
    public TripResponseDto getUserTrip(Long userNo,Long TripNo) {
        Trip trip = tripRepository.findByTripNo(TripNo);
        if(trip==null){
            return null;
        }
        Long tripUserNo =trip.getUser().getUserNo();
        if(tripUserNo.compareTo(userNo)!=0){
            return null;
        }
        List<PlaceCommon> placeCommonList =new ArrayList<>();
        List<TripPlace> tripPlaceList =new ArrayList<>();
        tripPlaceList=tripPlaceRepository.findAllByTrip_TripNo(trip.getTripNo());


        for (TripPlace tripPlace:tripPlaceList) {
            placeCommonList.add(tripPlace.getCommon());
        }
        TripResponseDto tripResponseDto = TripResponseDto
                .builder()
                .placeList(placeCommonList)
                .tripNo(trip.getTripNo())
                .userNo(trip.getUser().getUserNo())
                .endDate(trip.getEndDate())
                .startDate(trip.getStartDate())
                .tripName(trip.getTripName())
                .build();
        return tripResponseDto;
    }

    @Override
    public Trip getTripInfo(Long tripNo) {
        Trip trip = tripRepository.findByTripNo(tripNo);
        if(trip==null){
            return null;
        }else {
            return trip;
        }
    }

    @Override
    public LastTripResponseDto getUserPastOneTrip(Long userNo,Long TripNo) {
        Trip trip = tripRepository.findByTripNo(TripNo);
        if(trip==null){
            return null;
        }
        Long tripUserNo =trip.getUser().getUserNo();
        if(tripUserNo.compareTo(userNo)!=0){
            return null;
        }
        List<PlaceCommon> placeCommonList =new ArrayList<>();
        List<TripPlace> tripPlaceList =new ArrayList<>();
        List<Photo> photoList;
        tripPlaceList=tripPlaceRepository.findAllByTrip_TripNo(trip.getTripNo());

        photoList = photoRepository.findByTrip(trip);
        List<String> urlList = new ArrayList<>();
        String image="";
        photoList.forEach(photo ->{
            urlList.add(photo.getPhotoPath());
        });
        if(urlList!=null && urlList.size()!=0) {
            image = urlList.get(0);
        }else{
            image="data:image/jpeg;base64,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";
        }

        for (TripPlace tripPlace:tripPlaceList) {
            placeCommonList.add(tripPlace.getCommon());
        }
        LastTripResponseDto tripResponseDto = LastTripResponseDto
                .builder()
                .placeList(placeCommonList)
                .tripNo(trip.getTripNo())
                .userNo(trip.getUser().getUserNo())
                .endDate(trip.getEndDate())
                .startDate(trip.getStartDate())
                .tripName(trip.getTripName())
                .imageList(urlList)
                .thumbnail(image)
                .build();
        return tripResponseDto;
    }

    @Override
    public List<TripResponseDto> getUserTripList(Long userNo) {
        List<TripResponseDto> result = new ArrayList<>();

        List<Trip> list = tripRepository.findAllByUser_UserNo(userNo);

        List<PlaceCommon> placeCommonList;
        List<TripPlace> tripPlaceList ;

        for (Trip trip:list) {
            tripPlaceList = new ArrayList<>();
            placeCommonList = new ArrayList<>();
            tripPlaceList = tripPlaceRepository.findAllByTrip_TripNo(trip.getTripNo());
            for (TripPlace tripPlace: tripPlaceList) {
                placeCommonList.add(tripPlace.getCommon());
            }
            TripResponseDto tripResponseDto = TripResponseDto
                    .builder()
                    .placeList(placeCommonList)
                    .tripNo(trip.getTripNo())
                    .userNo(trip.getUser().getUserNo())
                    .endDate(trip.getEndDate())
                    .startDate(trip.getStartDate())
                    .tripName(trip.getTripName())
                    .build();
            result.add(tripResponseDto);
        }
        return result;
    }

    @Override
    public TripResponseDto getTodayTrip(Long userNo){
        List<Trip> list = tripRepository.findAllByUser_UserNo(userNo);
        String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));

        Date today,startDate,endDate;

        try {
             today = new Date(dateFormat.parse(todayStr).getTime());
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for(Trip trip :list) {
            try {
                startDate = new Date(dateFormat.parse(trip.getStartDate()).getTime());
                endDate = new Date(dateFormat.parse(trip.getEndDate()).getTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            int compare = startDate.compareTo(today);
            int compare_2 = endDate.compareTo(today);

            if (compare <= 0 && compare_2 >= 0) {
                List<PlaceCommon> placeCommonList =new ArrayList<>();
                List<TripPlace> tripPlaceList =new ArrayList<>();
                tripPlaceList=tripPlaceRepository.findAllByTrip_TripNo(trip.getTripNo());


                for (TripPlace tripPlace:tripPlaceList) {
                    placeCommonList.add(tripPlace.getCommon());
                }
                TripResponseDto tripResponseDto = TripResponseDto
                        .builder()
                        .placeList(placeCommonList)
                        .tripNo(trip.getTripNo())
                        .userNo(trip.getUser().getUserNo())
                        .endDate(trip.getEndDate())
                        .startDate(trip.getStartDate())
                        .tripName(trip.getTripName())
                        .build();
                return tripResponseDto;
            }
        }
        return null;

    }


    @Transactional
    @Override
    public List<LastTripResponseDto> getUserLastTripList(Long userNo) throws ParseException {
        List<LastTripResponseDto> result = new ArrayList<>();

        List<Trip> list = tripRepository.findAllByUser_UserNo(userNo);
        List<PlaceCommon> placeCommonList;
        List<TripPlace> tripPlaceList ;
        List<Photo> photoList;
        String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));


        for (Trip trip:list) {
            Date endDate = new Date(dateFormat.parse(trip.getEndDate()).getTime());
            Date today = new Date(dateFormat.parse(todayStr).getTime());
            Date startDate = new Date(dateFormat.parse(trip.getStartDate()).getTime());


            int compare = endDate.compareTo(today);
            int compare2 = startDate.compareTo(today);
            if(compare < 0 || compare2 <=0) {
                placeCommonList = new ArrayList<>();
                tripPlaceList = tripPlaceRepository.findAllByTrip_TripNo(trip.getTripNo());
                photoList = photoRepository.findByTrip(trip);
                List<String> urlList = new ArrayList<>();
                String image="";

                photoList.forEach(photo -> urlList.add(photo.getPhotoPath()));

                if(urlList.size() != 0) {
                    image = urlList.get(0);
                } else {
                    image="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBIRDxEPEBESEQ8PEA8PDw8QEhIREQ8PGBQZGRgUGBgcIS4lHB44IRgYJjgmLi8xNTU1GiQ7RDszPy40NzEBDAwMEA8QGBESHjQhISc1NDQ0NjUxMTY2MTQ0MTQ0NDQ0NDQ0NDU0ND00PzExND8xNDQ0ND4xNTQ0NjE0MTE0NP/AABEIAL0BCwMBIgACEQEDEQH/xAAbAAACAwEBAQAAAAAAAAAAAAAAAgEDBAUGB//EADwQAQACAQICBgkCBQMDBQAAAAEAAhEDEiExBEFRYXGRBQYTIlKBodHwYpIyQqKx4XLB8RQVIxZDU4LS/8QAGwEAAwEBAQEBAAAAAAAAAAAAAAECAwYEBQf/xAApEQEBAAIBAwMCBgMAAAAAAAAAAQIRAxITIQQxUUGRQmFxgcHwIiOh/9oADAMBAAIRAxEAPwDgwhCdA1EIQgBCEIAQhCAEISQiMBHCARwk2hARggEcJFpoCOEAjhItACMEAjhJtACMEkJISLVAI4QCOEi0AIwQCMEi0AI4QCOEi0wEYJISQk2gBHCARwmdpoCMEAlgSbTQEfEAjYkWh4OEITomAhCEAIQhACEJIRGAjhAI4SbQAkhAI4SLTARwgEYJFoARggEcJFoARwgEYJNqkBHCARwkWgBHCARgkWhARwkhGCRaYCOEAjBItNARwgEcJFoARggEcJNpgI4QCMEi0AJOJIRsSLTfPoQhOlecQhCAEIRgiNARwkhGCRaAEYIBHCTaaAjhAIwSLQAjhAI4SLQgI4SQkhJtUAjhAI4TO0AIwQCMEm0AI4QCOEi0wEYIBGCRaYCOEAjBItACMEkIwSLTARwgEcJNpoCMEAjhItACNiSEbEm0PnEIQnTvOIQIwRbMBHCARwkWgBJCARwk2mAjhAJISLQAjhAI4SLQAjhAJISLVAI4QCOEi0AIwSQjBJtCAjhAI4TO0wEYIBHCTaaAjhAI4SLQAjBAI4SLTARgkhGCTaYCMEAjhM7QgI4QCMEm0AI2JIRsSLQ+ZwCARwnVWsAEYIBHCRaAEkJIRwkWmgI4QCMEm0AI4QCGpetKt7pWtTNrPIO1mdoMEcJyv+9aftbaPClq11sW1kpVaYwnbVzbj+h5zgvrdqVdR9mW3bTTqttlcDusKDhGvD58OvLLkxguUj2oRwmL0V6R0+k0tehYK22+9gX3R/3x8vCdAIurfsqICOEAjBJtACOEAjhItNARggEcJNpgIwSQjBM7QAjBAI4SbTARwgEYJFpgIwQCOEi0AIwSQkhIuR6ARwkBGJnclTFIRsSCTM7mqYvmgRwgEYJ1lryAIwQCOEm0AIwSQjBItCAjhJCMEi1QCcz016RNLS16lrV1a6JatqnJs2rXj1PurOqE850n0LfUvq3sYrZva3EWy6lXGMKG3Tr1vF5HHGWduvCbv6PDZtewcbK4MGVVz83LN+v6L1Clb2pgvXPNb2t7yrVwnLHZyxnJPWdH9D9H6PqW6RTOpp1a4sNNSmnXc+0Ljl4VTCctvHrzvqVulbO2vt9e4V47NHQvapx5Hv4sdyByzPNMPlEw+Xjy2t0S2lelkrU9pTg0pbTs0HDZzbPutqnLKcOOPaerHTLavRqt1bVbm+16Wb13PH3XPDlxDPB65531o6E4tc2uWtWrQL12abk3q8TIp19vCcX0dXV0NSus0rU0tSu6mreuk32tbYxbC43UeT1OHEUy6aJbjX1YI4Tk+r/pg6VXUyVrbTvtxW27dTB7+ELAueYdnPM7IS+ptPPmAJISQjhIuStICOEAjBJuQ0AjBJCMEi5DQCOEAjBIuR6ARggEYmdzVMUhGCBJJnlmuYJJJCGZjlyNMcDScxMwzMMuVrjxHzJzKt0N0xvI0nHHzs1v0an7GT/1Ac63/Yznul+jU+Zn/aR7F+G/7WdtcnxOqukdKp2W/axjpmn+r9rOX7G3wW/ayfY2+C37WK0dVdWvTdP4seNbfaW16Vp/H9Lfacaulb4bftZdTTt2PkzPK6VLXWr0nT+L6W+0srr0+L6P2nKpV7GX18JjlnY0nl0q6tPi+jC1js3f6eFvBynD5zLTHZLqlXnXP1nny5a0mMrl+ldXZt1aaV6hrUprYdKtNWiZKIWxjdtM9WbceLnPXR9h0nS6LbU/8NtHSt0i+bU9nbSpqXAuJtLWo255914z0F9HTvR07UGicapwTnynj9f0dWnpTQ6N799C3sr+yvZsFalsV486nvcOxSZdzac8OnVdvoXT9G+pa9NTo1Oj0LV0zWvUu2sm/U2KILU5oruf5pPpb0U9K091NWt9avvGTdp6nDlXTylDkbuLg54noytUDHA5DxAj00w4VUqcq9R4dh3EyvM2nBuark+gfQ70ehfUvbU17Uxa1tvuFrN2onPivFXrxjM7IRjMcX8CT3r/AGqnDJ4IEcJI+HkSYd0+2gI4SAjhJvKXbARwkEYkXlPtpCSSCMSLyqnGkjEUk5mOXKvHiOQzKdbWrSlr3sVpSra1nkVOuU09JaNlDUqpqV0X/XYzUO3PU8mRcs8puS2LmOMurW3MMzHqdO0q786lD2dd9zJmtOPvJ2cHylep6T0aWvW2rSttN0y4uNu/G18PeOPLjMrOTL2xv2abwnvY3NpDaYn0noYX22mhRupar7oKvDurZ+TK+keltDTcamoGSts4U223YcnV7qd3XzJHa5LdTG7/AEquvCTe46G6RmYdX0poUoXtq1KpktxR93djh/NhEObLjpmkgl6IhYd1eImT6TPtcn1xv2p9eHzHlAjBLv8Aqj4KeZ9pJ0s/+Ov58p21zvw+F4VBGNM7DyJadMPgr9PtJr0v9Ff6ftIyzvwqWKvY0eda+RGr0anwV/aS46V+mp8q/aWHSP0n7a/aZZZ1c0or0anwV/aSyvR6fBXyJbXpD2V/aSyuq9lf2kwy5GmMjNemnXjbZXPAyhl7CUV6d0bds30ziqP8qrjA9vLz8Z0rhc23rS1cji1KWMnLgzN/2fRtbd7OvKw1DA56/Hn5zLuYfi3+yrMvw6WBpnXX+IpzP4kyV8ccZ4/o/pDSfTPSNW6NNHSvTT4HC1dtUr2uW/jme4/6LT2tfZae1xk9nTHLHZ2TjU9EaFOlOm6WnWt6W2NaVq1bcQyHc/SZTkw353o88cr0617tWn6W6M7nfUK4feybhB4HN54xz4Mr0fT2la+w4V3YL2SldmxWzu5cQPnN2j6B0K5zSts1K+/tePHNuXPl5Ss9XtIvuA2DR2WC3Kzvrx6kx35h1em872r/AHePY2p6V0Kc9Wi8XFbF0xz5eDMfpD1i06GNJNS6cE/gq8Ofb8vpOvb0P0a3/s0rzw0rWvzmbpnq9o2qeyK0sDjJuLPUPZ48ZHHn6aZTql/g8+9q604dPWm28baZs22GlUy34Ycvg/u7idC3rN0c3Y3u0zX3cb3HI7Pn2xD1avuoLp4se9YM7HC9nE4B850K+rGh22fcD+Q9/wCLl9JtyZ+k8fwyxnqPP8ua+tmnkxpamN2LK1Ep2h1vdw8Y/wD6q0dg7L73PuGMBuTjbtwZ+YZmm3qpT+W5zOenVwY49fOWHqtpbU3O/NttttcYwbdxjj8sc5OWfovHuqY+p/Jwul+tGpbdWlShta1uPvbsjWwJw68jk4+enonrb1a2n1WW+m83qNry8cy/pPq1euWhS1Qu8hcVrnlg4ryDOM8+tbonqza2HV2UHOaVBuGOHHGOfVNLyei6PMmv18omPqOr+6Z7+t/Gm3R4ZzfdfmY5V4duOPd3xL+t98+7pVwWf4lzamXH+m2Nvac+6btX1Vx/BeluIYtplcGeec8eGX5RdT1XsLstR4hXNQ4Y4r2dfLPVM5yeh+P+ncfUuVqetXSGoGytttBsGffrZbWDv90TsHt4Tr+tnSLVxQppu6y3KlnbnJULcOBwzjj3Tbf1d1AyUpbhZwBng4Dxef8AeF/VvVK7jT07InuDXdjGV48OfD5TWcnovHiI16jz5rzXS+m6mtbdqXbe9exnjs3c61XiV7s4med/pHQbaa79MrhrVWtcbkzgeT8pRsr8NfI+092HNhMZ0zx+Tz5ceW/8r5cjPHPW5y9bnnInY9nT4a+RDZX4a/tI+/j8F278uPAnY2V+Gv7STsr8Nf2kO/Pgu1XHzIwTs7K/DX9pDZT4a+RDv4/B9uusaNfwr9o50er+V+0pLxy80uKlp0Sna/0/aSdFp3/T7RK3e1845qPbM7jTh69Hp2PnLa6FOxlRfw8oxqEzyxq5VpoV7/OPWlez6sSup+cJPtJjlxrmS0069n1YbTqPr/zELzm9KrqU32LLQN3F4YbZ24evnxOp7pjlwr7mm/pGvbZijXO4G4jsr1rwwePfy4TJWlbatNSrqFcUta9ndelsWAx1mdo8OGJhp6Vu2zcreuR28sYc8Pzqml9J1MWqOd1r4TkWXfUzzH+/kZXi0fcldWvSbZ23tXHHbeoBbHMS7wTPE/ziNfp5pnvZtbOMYqce9zg6uWZw+mdJ9o4qrkriuD+LAIY4v+OvhL/RvQG9S7bFF41MhcLceXhMrw/LSc19o7nROnU1OBmtwy0eZxw+JmaxmTo2lTTEoYLO54rl+c0F/wA/GZZcDWcvjysGMMqLRxkXgp92LMyS0qIwybwUd2LMwijGGReCq70PiRtgRiReGnOWF2wSNJmd4qqZxTqaRYSwIjXiZ4PMmWvovRN3/jPe2DxeBTG0Hn/KZ7Z0IrWKXPGaxtgsxy94y26Hptm7Su9q13YBw8/nx5yq/o3RtzoP/jNI424UOQceffzm5rI2yO5yT2tn7n0Y33jHX0foiJpUEzhDHPt7f9oup6K0LFaumFaBUDBwyL354HHn5uduJEnu8ku+q/cdGN+kZf8AtujsKezNpt60bbeW5P4vB4S7Q6Np0qUrSuKmDJufOWQzFeXk+uV+5zjx+HjK3O2OXJlrqPbnx3Rix3f1fefoFwfD20l+48/8xy/h9ZmLnZXzfvJL9x5/5k3A9tPtJJeZjUjF5FxG2kvHLzMWjlpFwG2ot+ZJLxMPJ7HH1JlLxy8zuB7Kej9Pc2xnO7hY90XuMd/nKj0MK+/g4bcD88/8zSakcvIvHDWdG6JTT/hM8VF4teXAflNI4mQvHNSReOKmTUXjF5lLxy8i8cPqrSXjF5lLxy8m8cHU1F45eZS8YvIvHB1NRaMWmYvLBkXjh9daC0ctMxaSXkXjh9bSMYZnLxy8zvFFTOrsyZWXklpllwxc5KfECvdIGTnumOXA0x5hs7vpFaPY+Uf5Sc90xy4GuPKoaSNs0cPwhg/AmN4Wncj5llklkgY7/pJU/P8AmfoT4hjU8PIjl/DyJUPhJy9p5km4haXjF5n3Pd5/5klpNxG2ut/D6x638POZK3/OMsrfu/v95NwNrLeHnGLeP585nrc7PrGL9z5yLiNtBZ7/ACk7vH5ykfHzk7u98mRcT2uLxy8z7u98mSWk3Ebaq3llbvZ9JjreW1v+cPtM7ge2sv3f0xs930ZlLn4n2jVfzP8AiTcA0j4+UYt4+Upq+H0+0ep+YzIuJ7WlyOW8ZTtfwftJB/MyLgNry0at5Ru8frGL+PlIuJ7aS/fHL/nGZS3j5Enf+Yk3A2kvHNSZS8YvIuI21l/DyI5eZC8at5ncD21F4xfxmatzv8/8R90i4KmS/dJzKS0nd3zO8Spm+fp30/o+0gp/p+W37Sreya2zOs08a3b+nPht+0hP0W+n/wCYuTs+rGrh+I8LMAXZ+m3n/iLamOpPGQ6n+r93+I2e984wgO+SX73zftB1HtfNiupnqIjWl+9837Sa6ne+cqYpF0wNtda3xY/+zHrqvXc87THXPbHM9sm4QNTf9VfO0mvieVvtMu57Y27Hf4yLibZW3g/K32k+07v7zKXPhJdW36T6/eTcQtLsYv3SnT1v0n1ZdvmdxBy/6f7xy52Hmyjfn8PtGoSbie2itzu8lllbnaftlBTvkhIuI201ufF/SSyt+88pjzJL+PmfaRcTbd3h52kZ8PrMxbx84Gr3Pn/iRcTax/OMYt4ygtHrJsPa0vHreUhGkXEbaTU73zjGo9r5zNVjDIuJtRqd/wDeNv7/AKMylu6Nuk3EP//Z";
                }

                for (TripPlace tripPlace : tripPlaceList) {
                    placeCommonList.add(tripPlace.getCommon());
                }
                LastTripResponseDto tripResponseDto = LastTripResponseDto
                        .builder()
                        .placeList(placeCommonList)
                        .tripNo(trip.getTripNo())
                        .userNo(trip.getUser().getUserNo())
                        .endDate(trip.getEndDate())
                        .startDate(trip.getStartDate())
                        .tripName(trip.getTripName())
                        .imageList(urlList)
                        .thumbnail(image)
                        .build();
                result.add(tripResponseDto);
            }
        }

        return result;
    }



    //추후 어떻게 수정될지는 모르지만 확시나, table에 존재하는지 확인해야한다면... 일단 테스틑 필요함
    private String checkCommonNoTable(Long commonNo){
        if(cultureRepository.existsById(commonNo)){
            return "restaurant";
        }else if(festivalRepository.existsById(commonNo)){
            return "festival";
        }else if(leisureRepository.existsById(commonNo)){
            return "leisure";
        }else if(cultureRepository.existsById(commonNo)){
            return "culture";
        } else if (shoppingRepository.existsById(commonNo)) {
            return "shopping";
        } else if (touristRepository.existsById(commonNo)) {
            return "tourist";
        }
        else {
            return "";
        }
    }

    private CultureDetailDto getCultureDetail(Long commonNo){
        PlaceCommon common = placeCommonRepository.findByCommonNo(commonNo);
        CultureDetail culture = cultureRepository.findByCommon(common);
        CultureDetailDto detail = CultureDetailDto.builder()


                .chkCreditcard(culture.getChkCreditcard()).chkPet(culture.getChkPet())
                .parking(culture.getParking()).restDate(culture.getRestDate())
                .useFee(culture.getUseFee()).useTime(culture.getUseTime())
                .scale(culture.getScale()).spendTime(culture.getSpendTime())
                .build();

        return detail;
    }
    private FestivalDetailDto getFestivalDetail(Long commonNo){
        PlaceCommon common = placeCommonRepository.findByCommonNo(commonNo);
        FestivalDetail festival = festivalRepository.findByCommon(common);;
        FestivalDetailDto detail = FestivalDetailDto.builder()


                .startDate(festival.getStartDate()).endDate(festival.getEndDate())
                .place(festival.getPlace()).festivalGrade(festival.getFestivalGrade())
                .placeInfo(festival.getPlaceInfo()).playTime(festival.getPlayTime())
                .program(festival.getProgram()).spendTime(festival.getSpendTime())
                .useTime(festival.getUseTime())

                .build();

        return detail;
    }
    private LeisureDetailDto getLeisureDetail(Long commonNo){
        PlaceCommon common = placeCommonRepository.findByCommonNo(commonNo);
        LeisureDetail leisure = leisureRepository.findByCommon(common);;
        LeisureDetailDto detail = LeisureDetailDto.builder()


                .accomCount(leisure.getAccomCount()).chkCreditcard(leisure.getChkCreditcard())
                .chkPet(leisure.getChkPet()).infoCenter(leisure.getInfoCenter())
                .openPeriod(leisure.getOpenPeriod()).parking(leisure.getParking())
                .useTime(leisure.getUseTime())
                .build();

        return detail;
    }
    private RestaurantDetailDto getRestaurantDetail(Long commonNo){
        PlaceCommon common = placeCommonRepository.findByCommonNo(commonNo);
        RestaurantDetail restaurant = restaurantRepository.findByCommon(common);;
        RestaurantDetailDto detail = RestaurantDetailDto.builder()


                .chkCreditcard(restaurant.getChkCreditcard()).infoCenter(restaurant.getInfoCenter())
                .firstMenu(restaurant.getFirstMenu()).openDate(restaurant.getOpenDate())
                .openTime(restaurant.getOpenTime()).packing(restaurant.getPacking())
                .parking(restaurant.getParking()).reservation(restaurant.getReservation())
                .restDate(restaurant.getRestDate()).scale(restaurant.getScale())
                .seat(restaurant.getSeat()).smoking(restaurant.getSmoking()).treatMenu(restaurant.getTreatMenu())
                .build();

        return detail;
    }
    private TouristSpotDetailDto getTourSpotDetail(Long commonNo){
        PlaceCommon common = placeCommonRepository.findByCommonNo(commonNo);
        TouristSpotDetail tour = touristRepository.findByCommon(common);;
        TouristSpotDetailDto detail = TouristSpotDetailDto.builder()


                .accomCount(tour.getAccomCount()).chkCreditcard(tour.getChkCreditcard())
                .chkPet(tour.getChkPet()).heritage1(tour.getHeritage1())
                .heritage2(tour.getHeritage2()).heritage3(tour.getHeritage3())
                .openDate(tour.getOpenDate()).parking(tour.getParking())
                .restDate(tour.getRestDate()).useSeason(tour.getUseSeason())
                .useTime(tour.getUseTime())
                .build();

        return detail;
    }
    private ShoppingDetailDto getShoppingDetail(Long commonNo){
        PlaceCommon common = placeCommonRepository.findByCommonNo(commonNo);
        ShoppingDetail shoppingDetail  =shoppingRepository.findByCommon(common);
        ShoppingDetailDto placeCommonDto = ShoppingDetailDto.builder()


                .chkCreditcard(shoppingDetail.getChkCreditcard()).chkPet(shoppingDetail.getChkPet())
                .cultureCenter(shoppingDetail.getCultureCenter()).fairDay(shoppingDetail.getFairDay())
                .infoCenter(shoppingDetail.getInfoCenter()).openDate(shoppingDetail.getOpenDate())
                .openTime(shoppingDetail.getOpenTime()).parking(shoppingDetail.getParking())
                .restDate(shoppingDetail.getRestDate()).restRoom(shoppingDetail.getRestRoom())
                .saleItem(shoppingDetail.getSaleItem()).saleItemCost(shoppingDetail.getSaleItemCost())
                .scale(shoppingDetail.getScale()).shopGuide(shoppingDetail.getShopGuide())
                .build();
        return placeCommonDto;
    }


}
