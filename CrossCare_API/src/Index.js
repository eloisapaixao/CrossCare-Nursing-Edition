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
            res.send({ message: "Usuário autenticado!" });
        } else {
            res.send({ message: "Usuário não autenticado" });
        }
    } catch (error) {
        console.error({ message: "Erro ao buscar usuários:", error });
        res.status(500).json({ error: "Erro ao buscar usuários" });
    }
});

// Rota para obter as palavras cruzadas atuais
app.get('/crossword', async (req, res) => {
    const p = await pegarPalavras();
    res.send(p);
});

// Rota para avançar para a próxima fase do jogo
app.post('/next', async(req, res) => {
    const p = await pegarPalavras();
    res.json(p);
});

async function pegarPalavras() {
    const palavrasRef = collection(db, 'Palavras');

    const querySnapshot = await getDocs(palavrasRef);

    const palavras = [];

    querySnapshot.forEach((doc) => {
        // Adiciona cada palavra aos palavras array
        if (doc.data().tamanho == 7)
            palavras.push({ id: doc.id, palavra: doc.data().palavra, dica: doc.data().dica, categoria: doc.data().Categoria });
    });

    const palavrasEmbaralhadas = shuffleArray(palavras);

    // Pega as primeiras 5 palavras
    const cincoPalavras = palavrasEmbaralhadas.slice(0, 8);

    return cincoPalavras;
}

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
}

app.listen(3000, async () => {
    console.log('API FUNCIONOU!!')
})