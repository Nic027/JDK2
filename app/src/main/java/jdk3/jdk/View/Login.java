package jdk3.jdk.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import jdk3.jdk.R;

public class Login extends AppCompatActivity {

    EditText user, pass;
    Button login;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference Myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        referenciar();
    }

    private void referenciar() {
        login = findViewById(R.id.btnlogin);
        user = findViewById(R.id.User);
        pass = findViewById(R.id.Password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
    }

    private void validar() {
        String Usuario = user.getText().toString();
        String Contra = pass.getText().toString();

        // FirebaseDatabase database = FirebaseDatabase.getInstance();
        // DatabaseReference myRef = database.getReference("Users");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        Query query = reference.orderByChild("usuario").equalTo(Usuario);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String usuarioEncontrado = userSnapshot.child("usuario").getValue(String.class);
                        String contraEncontrada = userSnapshot.child("contraseña").getValue(String.class);
                        if (Objects.equals(Usuario,usuarioEncontrado) && Objects.equals(Contra,contraEncontrada)) {
                            Toast.makeText(Login.this, "Credenciales correctas", Toast.LENGTH_SHORT).show();
                        }
                        else if (Objects.equals(Usuario,usuarioEncontrado) && !Objects.equals(Contra,contraEncontrada)){
                            Toast.makeText(Login.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                        else if (!Objects.equals(Usuario,usuarioEncontrado) && Objects.equals(Contra,contraEncontrada)){
                            Toast.makeText(Login.this, "Usuario incorrect incorrecta", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                else {
                    Toast.makeText(Login.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Maneja errores de la consulta aquí
            }
        });
    }
}