window.onload = () =>{

    //validar formulario luego enviar
    const btnEnviarFormulario = document.getElementById('enviarForm');
    btnEnviarFormulario.addEventListener('click', validarFormulario);
    const error = document.querySelector('#Error');

    //Validación de formularios
    function validarFormulario() {
        console.log('entrando a la validación de formulario');

        let regexLetras = /^[a-zA-ZÁ-ÿ]{3,10}$/;
        let regexRut = /\d{1,2}[.]\d{3}[.]\d{3}[-][\d{1}?(k)?]/;
        let regexCel = /[+569]\d{11}/;
        let regexEmail = /[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+/;
        let regexPedido = /(\w{4,20})/;
        let advertencia = '';
        let flag = false;
        error.innerHTML = '';

        if (!regexLetras.test(nombre.value)) {
            advertencia += `el nombre no es válido <br>`
            console.log('nombre invalido');
            flag = true;
        }
        if (!regexLetras.test(apellido.value)) {
            advertencia += `el apellido no es válido <br>`
            console.log('apellido invalido');
            flag = true;
        }
        if (!regexRut.test(rut.value)) {
            advertencia += `el rut no es válido <br>`
            console.log('rut invalido');
            flag = true;
        }
        if (!regexCel.test(celular.value)) {
            advertencia += `el celular no válido <br>`
            console.log('telefono invalido');
            flag = true;
        }
        if (!regexEmail.test(email.value)) {
            advertencia += `el email no es válido <br>`
            console.log('email invalido');
            flag = true;
        }
        if (!regexPedido.test(nPedido.value)) {
            advertencia += `el pedido no es valido <br>`
            console.log('el pedido no es válido');
            flag = true;
        }
        if (fecha.value == '') {
            advertencia += `Debe escoger una fecha <br>`
            console.log('fecha invalida')
            flag = true;
        }
        if (tipoReclamo.value == 'seleccionar') {
            advertencia += `Debe escoger un tipo de reclamo <br>`
            console.log('tipo invalida')
            flag = true;
        }
        if (flag) {
            console.log('bandera igual a true');
            error.innerHTML = advertencia;
        } else {
            console.log('se ha autorizado a interactuar con la api')
            enviarFormulario();
        }
    }



    function enviarFormulario() {
        console.log('Ejecutando el envio del formulario...');

        const url = 'http://localhost:8080/api/ingresar_reclamo';

        fetch(url, {
            method: 'POST',
            body: JSON.stringify({
                nombre: nombre.value,
                apellido: apellido.value,
                rut: rut.value,
                telefono: celular.value,
                email: email.value,
                fecha: fecha.value,
                pedido: nPedido.value,
                tipo_reclamo: tipoReclamo.value,
                descripcion: descripcion.value

            }),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(respuesta => {
            if (respuesta.status == 201) {
                alert('Reclamo ingresado');
            } else if (respuesta.status == 409) {
                alert('no se pudo ingresar')
            } else if (respuesta.status == 500) {
                alert('Surgió un problema')
            }
        }).catch(error => console.log(error))


    }


}