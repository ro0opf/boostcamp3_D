package com.teamdonut.eatto.ui.home;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import com.teamdonut.eatto.common.helper.RealmDataHelper;
import com.teamdonut.eatto.data.Board;
import com.teamdonut.eatto.data.User;
import com.teamdonut.eatto.model.HomeAPI;
import com.teamdonut.eatto.model.ServiceGenerator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class HomeViewModel extends ViewModel {
    private ObservableArrayList<User> users = new ObservableArrayList<>();
    private ObservableArrayList<Board> recommendBoards = new ObservableArrayList<>();
    private ObservableArrayList<Board> anyBoards = new ObservableArrayList<>();
    private ObservableField<User> user = new ObservableField<>();

    private Realm realm = Realm.getDefaultInstance();

    private UserRankingAdapter userRankingAdapter = new UserRankingAdapter(users);
    private BoardRecommendAdapter boardRecommendAdapter = new BoardRecommendAdapter(recommendBoards);

    private CompositeDisposable disposables = new CompositeDisposable();
    private HomeAPI service = ServiceGenerator.createService(HomeAPI.class, ServiceGenerator.BASE);
    private HomeNavigator mNavigator;

    public HomeViewModel(HomeNavigator navigator) {
        this.mNavigator = navigator;
    }

    public void fetchRecommendBoardList(String longitude, String latitude) {
        disposables.add(
                service.getRecommendBoards(longitude, latitude)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doAfterSuccess(data -> {
                            if(data.size() == 0){
                                boardRecommendAdapter.setItem(anyBoards);
                            }
                        })
                        .subscribe(data -> {
                                    boardRecommendAdapter.setItem(data);
                                }, e -> {
                                    e.printStackTrace();
                                }
                        )
        );
    }

    public void fetchAnyBoardList() {
        disposables.add(
                service.getAnyBoards()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> {
                                    anyBoards.addAll(data);
                                    boardRecommendAdapter.notifyDataSetChanged();
                                }, e -> {
                                    e.printStackTrace();
                                }
                        )
        );
    }

    public void fetchRankUsersList() {
        disposables.add(
                service.getTopTenUsers()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> {
                                    userRankingAdapter.setItem(data);
                                }, e -> {
                                    e.printStackTrace();
                                }
                        )
        );
    }

    public void fetchRankUser() {
        User originUser = realm.copyFromRealm(realm.where(User.class).findFirst());

        disposables.add(
                service.getRankUser(originUser.getKakaoId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doAfterSuccess(data -> {
                            updateUserInfo(data);
                        })
                        .subscribe(data -> {
                                    user.set(data);
                                }, e -> {
                                    e.printStackTrace();
                                }
                        )
        );
    }

    private void updateUserInfo(User user) {
        RealmDataHelper.updateUser(realm, user);
    }

    public void onSearchClick() {
        mNavigator.goToMapSearch();
    }

    public void onDestroyViewModel() {
        realm.close();
        disposables.dispose();
    }

    public ObservableField<User> getUser() {
        return user;
    }

    public UserRankingAdapter getUserRankingAdapter() {
        return userRankingAdapter;
    }

    public BoardRecommendAdapter getBoardRecommendAdapter() {
        return boardRecommendAdapter;
    }
}
