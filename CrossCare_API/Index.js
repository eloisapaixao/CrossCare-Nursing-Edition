const express = require("express")
const app = express()

app.use(express.json())

const { initializeApp } = require('firebase/app')
const { getFirestore, addDoc, collection, query, where, getDocs } = require('firebase/firestore')

const firebaseConfig = {
    apiKey: "AIzaSyDhadwtPHkSDKEtMt3HPWcusyeBZhfZKBM",
    authDomain: "crosscareapi.firebaseapp.com",
    projectId: "crosscareapi",
    storageBucket: "crosscareapi.appspot.com",
    messagingSenderId: "659114285712",
    appId: "1:659114285712:web:e41045bde536a181555615",
    measurementId: "G-JM0WHTW375"
};

const firebaseApp = initializeApp(firebaseConfig)
const db = getFirestore(firebaseApp)

app.post('/cadastro', async (req, res) => {
    try {
        const usuario = await addDoc(collection(db, 'Usuario'), {
            email: req.body.email,
            senha: req.body.senha
        });

        console.log(usuario);

        return res.status(201).json({ id: usuario.id });
    } catch (error) {
        console.error("Erro ao criar usuário:", error);
        res.status(500).json({ error: "Erro ao criar usuário" });
    }
});

app.post("/usuarios", async (req, res) => {
    try {
        const usuariosRef = collection(db, 'Usuario');
        const q = query(usuariosRef, where("email", "==", req.body.email), where("senha", "==", req.body.senha));
        const querySnapshot = await getDocs(q);

        if (!querySnapshot.empty) {
            res.send("Usuário autenticado!");
        } else {
            res.send("Usuário não autenticado");
        }
    } catch (error) {
        console.error("Erro ao buscar usuários:", error);
        res.status(500).json({ error: "Erro ao buscar usuários" });
    }
});



app.listen(3000, () => {
    console.log('API FUNCIONOU!!')
})