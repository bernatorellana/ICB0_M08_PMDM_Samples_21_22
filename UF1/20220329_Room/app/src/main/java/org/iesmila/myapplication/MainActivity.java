package org.iesmila.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import org.iesmila.myapplication.dao.AppDatabase;
import org.iesmila.myapplication.dao.RatingDAO;
import org.iesmila.myapplication.dao.SongDAO;
import org.iesmila.myapplication.dao.UserDAO;
import org.iesmila.myapplication.model.RatedSong;
import org.iesmila.myapplication.model.Rating;
import org.iesmila.myapplication.model.Song;
import org.iesmila.myapplication.model.User;
import org.iesmila.myapplication.model.UserRatedSongs;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txvText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvText = findViewById(R.id.txvText);


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        UserDAO userDAO =  db.userDao();
        SongDAO songDAO =  db.songDao();
        RatingDAO ratingDAO =  db.ratingDao();

        //------------------------------------------------------
        // esborrar totes les dades
        db.clearAllTables();

        //-------------------------------------------------------
        User paco = new User(12, "Paco");
        Song s1 = new Song(23, "Smells like teen spirit", "Nirvana");
        Song s2 = new Song(45, "How do yo remind me", "Nickelback");
        Song s3 = new Song(76, "Faded", "Alan Walker");
        Rating r1 = new Rating(12, 23, 4);
        Rating r2 = new Rating(12, 45, 3);
        User maria = new User(16, "Maria");
        Rating r3 = new Rating(16, 76, 5);
        Rating r4 = new Rating(16, 45, 4);
        
        //---------------------------------
        userDAO.insertAll(paco, maria);
        songDAO.insertAll(s1, s2, s3);
        ratingDAO.insertAll(r1,r2,r3,r4);
        //---------------------------------

        List<UserRatedSongs> userRatedSongs = userDAO.getAll();

        String out="";

        for(UserRatedSongs urs: userRatedSongs) {
            out +=          urs.user.name +":\n";
            for(RatedSong rs:urs.ratedSongs){
                out +=          "\t <" + rs.rating.rating +"> "+rs.song.name+":\n";
            }
        }
        txvText.setText(out);

    }


}