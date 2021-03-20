window.onload = () => {
       

    //variables para traer los registros
    const btnTraerTodo = document.getElementById('cargarTodos');
     btnTraerTodo.addEventListener('click', cargarTodo);

    //varibales para el buscador selector de registros
    const selectValor = document.getElementById('tipoBusqueda');
    selectValor.addEventListener('change', enviarBusqueda);

    //variables para el boton buscar 
    const btnBuscarRegistro = document.getElementById('btnBuscar');

    //variables para boton de actualizar estado
    const btnActualizarEstado = document.getElementById('btnModificar');
    btnActualizarEstado.addEventListener('click', actualizarEstado);
    



    //Validación de formularios
    function validarFormulario(){
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
        if(fecha.value == ''){
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

    //funcion para busquedas por combobox
    function enviarBusqueda(e) {

        switch (e.target.value) {
            case 'rut':
                console.log('entrnado switch de rut'); 
                btnBuscarRegistro.addEventListener('click', function(){buscarRegistro('http://localhost:8080/api/buscar_reclamo_r', 'rut')});
                break;
            case 'fecha':
                console.log('entramos al switch de fecha');
                btnBuscarRegistro.addEventListener('click', function () { buscarRegistro('http://localhost:8080/api/buscar_reclamo_fecha', 'fecha') });
                break;
            case 'nPedido':
                console.log('entrnado switch de pedido');
                btnBuscarRegistro.addEventListener('click', function(){buscarRegistro('http://localhost:8080/api/buscar_reclamo_p', 'pedido')});
                break;
            case 'estado':
                console.log('entrando en switch de estado');
                btnBuscarRegistro.addEventListener('click', function () { buscarRegistro('http://localhost:8080/api/buscar_reclamo_estado', 'estado')} )
                break;
            case 'id':
                console.log('entrando al switch de id');
                btnBuscarRegistro.addEventListener('click', buscarPorId);
                break;

        }

    }
    function buscarPorId (){
        
        let id = buscar_porSelect.value;

        const url = 'http://localhost:8080/api/buscar_reclamo_id/'+id;

        fetch(url, {
            method: 'POST',
            body: JSON.stringify({
                id: buscar_porSelect.value

            }),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(respuesta => {
            if (respuesta.status == 200) {
                return respuesta
               
            } else if (respuesta.status == 404) {
                alert('No se pudo actualizar')
            } else if (respuesta.status == 500) {
                alert('Surgió un problema')
            }
        
        }).then(respuesta => respuesta.json())
          .then(datos => mostrarRegistros(datos))
        .catch(error => console.log(error))


    }
    //funcion para comunicarnos con la api con el select
    function buscarRegistro(url,nombre) {

        console.log('ejecutando fetch api: '+nombre);      
        let d = {};
        switch(nombre){
            case 'rut':
                d = {rut: buscar_porSelect.value}
                break;
            case 'fecha':
                console.log('se ha escogido parametro fecha');
                d = {fecha: buscar_porSelect.value}
                break;
            case 'pedido':
                d = {pedido: buscar_porSelect.value}
                break;
            case 'estado':
                d = { estado: buscar_porSelect.value }
                break;
          

        }
        //const d = (nombre === 'rut') ? { rut: buscar_porSelect.value } : { pedido: buscar_porSelect.value};
        fetch(url, {
            method: 'POST',
            body: JSON.stringify(
                d
            ),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(respuesta => {
            if (respuesta.status == 200) {
               
               /* var variable = JSON.stringify(respuesta)
                console.log('esta es respuesta la: '+ variable);*/
                //alert('Registro encontrado');
                return respuesta;
                
            } else if (respuesta.status == 404) {
                alert('no encontrado')                
            }
        }
        ).then(respuesta => respuesta.json())
            .then(datos => mostrarRegistros(datos))
            .catch(error => console.log(error))
    

    }

    function mostrarRegistros(datos) {
        console.log('entrando a la funcion mostrar registros');

        const contenidoTabla = document.querySelector('.lista-tabla');

        let html = '';
        if(!datos.length){
            console.log('ha escogido un objeto');
            const { id, nombre, apellido, email, rut, telefono, tipo_reclamo, descripcion, fecha, pedido, estado} = datos;

            html += `
        <tr>
                <td>${id}</td>
                <td>${nombre}</td>
                <td>${apellido}</td>
                <td>${rut}</td>
                <td>${email}</td>
                <td>${telefono}</td>                
                <td>${fecha}</td>
                <td>${tipo_reclamo}</td>
                <td>${pedido}</td>
                <td>${descripcion}</td>  
                <td>${estado}</td> 
                </tr>      
        `;


        }
       else datos.forEach(perfil => {
            const { id, nombre, apellido, email, rut, telefono, tipo_reclamo, descripcion, fecha, pedido, estado } = perfil;
            console.log('ha escogido un array');
            html += `
        <tr>
                <td>${id}</td>
                <td>${nombre}</td>
                <td>${apellido}</td>
                <td>${rut}</td>
                <td>${email}</td>
                <td>${telefono}</td>                
                <td>${fecha}</td>
                <td>${tipo_reclamo}</td>
                <td>${pedido}</td>
                <td>${descripcion}</td>
                <td>${estado}</td>  
                </tr>      
        `;

        });

        contenidoTabla.innerHTML = html;
    }

    function actualizarEstado(){
        console.log('ejecutando la actualizacion de el estado')

        let ID = buscar_ID.value;
        console.log('id mostrada es: '+ID);

        const url = 'http://localhost:8080/api/update/'+ID;

        console.log('la url seria: '+url);

        fetch(url, {
            method: 'PUT',
            body: JSON.stringify({
                estado : nuevo_estado.value

            }),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(respuesta => {
            if (respuesta.status == 200) {
                alert('Estado actualizado');
            } else if (respuesta.status == 404) {
                alert('No se pudo actualizar')
            } else if (respuesta.status == 500) {
                alert('Surgió un problema')
            }
        }).catch(error => console.log(error))

    }


    /*
   primer paso para reconocimiento de options de un select por valores
     function enviarBusqueda(e){

        if(e.target.value== 'rut'){
            console.log('hemos filtrado por rut');
         } else if(e.target.value == 'nPedido'){
                console.log('hemos filtrado por pedido');
            }

    }
   
   
    varibales para el buscador de registros con objetos
    const btnBusqueda = document.getElementById('buscar_porSelect');


    const datosBusqueda = {
        rut :'',
        fecha :'',
        pedido :''
    }

    btnBusqueda.addEventListener('change', (e) => {
        datosBusqueda.rut = e.target.value;
        console.log(datosBusqueda);
    });
    
    
    
    */








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
                alert('Usuario ingresado');
            } else if (respuesta.status == 409) {
                alert('no se pudo ingresar')
            } else if (respuesta.status == 500) {
                alert('Surgió un problema')
            }
        }).catch(error => console.log(error))


    }

    function cargarTodo() {
        console.log('ejecutando el get de datos...');

        const url = 'http://localhost:8080/api/reclamos';

        fetch(url)
            .then(respuesta => respuesta.json())
            .then(reclamos => mostrarTablaTodo(reclamos))
            .catch(error => console.log(error))
    }


    function mostrarTablaTodo(datos) {
        console.log('entrando a la funcion mostrar tabla');

        const contenidoTabla = document.querySelector('.lista-tabla');

        let html = '';

        datos.forEach(perfil => {
            const { id, nombre, apellido, email, rut, telefono, tipo_reclamo, descripcion, fecha, pedido, estado } = perfil;

            html += `
        <tr>
                <td>${id}</td>
                <td>${nombre}</td>
                <td>${apellido}</td>
                <td>${rut}</td>
                <td>${email}</td>
                <td>${telefono}</td>                
                <td>${fecha}</td>
                <td>${tipo_reclamo}</td>
                <td>${pedido}</td>
                <td>${descripcion}</td>  
                <td>${estado}</td>  
                </tr>      
        `;

        });

        contenidoTabla.innerHTML = html;
    }

}

/*
funcion mostrar usuarios por rut 

 function mostrarRegistrosRut(datos) {
        console.log('entrando a la funcion mostrar tabla por rut');

        const contenidoTabla = document.querySelector('.lista-tabla');

        let html = '';

        datos.forEach(perfil => {
            const { id, nombre, apellido, email, rut, telefono, tipo_reclamo, descripcion, fecha, pedido } = perfil;

            html += `
        <tr>
                <td>${id}</td>
                <td>${nombre}</td>
                <td>${apellido}</td>
                <td>${rut}</td>
                <td>${email}</td>
                <td>${telefono}</td>
                <td>${fecha}</td>
                <td>${tipo_reclamo}</td>
                <td>${pedido}</td>
                <td>${descripcion}</td>
                </tr>
        `;

        });

        contenidoTabla.innerHTML = html;
    }






*/