window.onload = () =>{

    const btnIniciar = document.getElementById('login');

    btnIniciar.addEventListener('click', validarLogin);
    const error = document.getElementById('Error');

   
    function validarLogin(){
        let regexUsu = /[a-zA-ZÁ-ÿ._-]{3,10}/;
        let regexPass = /[a-zA-ZÁ-ÿ0-9._-]{3,10}/;

        let advertencia = '';
        let flag = false;
        error.innerHTML = '';

        if (!regexUsu.test(nombreUsuario.value)) {
            advertencia += `el nombre de usuario no es válido <br>`
            console.log('usuario invalido');
            flag = true;
        }
        if (!regexPass.test(passUsuario.value)) {
            advertencia += `el nombre de usuario no es válido <br>`
            console.log('pass invalidaa');
            flag = true;
        }
        if (flag) {
            console.log('bandera igual a true');
            error.innerHTML = advertencia;
        } else {
            console.log('se ha autorizado a interactuar con la api')
            consultarLogin();
        }

    }

    function consultarLogin(){

        const url = 'http://localhost:8080/login';

        fetch(url,{
            method: 'POST',
                body: JSON.stringify({
                    nombre: nombreUsuario.value,
                    pass: passUsuario.value
                }),
                    headers: {
                'Content-Type': 'application/json'
            }
        }).then(respuesta =>{
            if(respuesta.status == 200){
                alert('Bienvenido');
                window.location.href = 'http://127.0.0.1:5500/vista.html'
            }else if(respuesta.status == 404){
                alert('Usuario y/o contraseña incorrectas')
            }
        })

    }

}