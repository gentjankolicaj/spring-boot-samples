package springboot.samples.tenisstatemachine;

public enum TenisStateType {

    LOVE,
    _15_LOVE, _30_LOVE, _40_LOVE,
    LOVE_15, LOVE_30, LOVE_40,
    _15_ALL, _15_30, _30_15,
    _40_15, _30_ALL, _15_40, _30_40, _40_30,
    SERVER_WIN, OPPONENT_WIN, DEUCE, AD_IN, AD_OUT,
    END;


    public static TenisStateType[] intermediateStates() {
        return new TenisStateType[]{
                _15_LOVE, _30_LOVE, _40_LOVE,
                LOVE_15, LOVE_30, LOVE_40,
                _15_ALL, _15_30, _30_15,
                _40_15, _30_ALL, _15_40, _30_40, _40_30,
                SERVER_WIN, OPPONENT_WIN, DEUCE, AD_IN, AD_OUT,
        };
    }

}
