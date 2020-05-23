package com.yousuf.notificationapp.fragements;

import androidx.fragment.app.Fragment;

public interface MediatorInterface {
    void changeFragmentTo(Fragment fragmentToDisplay, String fragmentTag);
}
