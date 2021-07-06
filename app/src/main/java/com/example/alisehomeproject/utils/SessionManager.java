package com.example.alisehomeproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int	PRIVATE_MODE = 0;
    public static final String	KEY_SESSION_ID	 = "sessionId";
    private static String		PREF_NAME		 = "com.example.alisehomeproject.sessionPref";
    private static SessionManager sessionInstance=null;
    public SessionManager(Context context)
    {
        this._context = context;
        pref = this.getPreference();
        editor = pref.edit();
    }

    public static SessionManager getInstance(Context context){
        if(sessionInstance == null)
        {
            sessionInstance = new SessionManager(context);
        }
        return sessionInstance;
    }
    private SharedPreferences getPreference(){
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return  pref;
    }
    public void commit()
    {
        editor.commit();
    }
    public void setSessionID(String sessionId)
    {

        editor.putString(KEY_SESSION_ID, sessionId);
    }
    public String getSessionID()
    {
        return pref.getString(KEY_SESSION_ID, null);
    }
}
