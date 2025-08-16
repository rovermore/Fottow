package com.fottow.fottow.presentation.conditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fottow.fottow.presentation.widgets.FTopBar
import com.fottow.fottow.presentation.widgets.ScreenContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(
    onBackPressed: () -> Unit
) {
    ScreenContainer(
        topBar = {
            FTopBar(
                icon = com.fottow.fottow.R.drawable.ic_close
            ) {
                onBackPressed()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            //Privacy Policy
            Text(
                text = "POLÍTICA DE PRIVACIDAD\n" +
                        "Última actualización: 5 de agosto de 2025\n" +
                        "En Fottow Tech, S.L. (en adelante «fottow», «nosotros», «nuestro», «nos» o «la app») creemos que la privacidad es un derecho fundamental y una parte esencial de la confianza que depositas en nosotros cuando decides usar nuestra aplicación. Por ello hemos diseñado nuestra Política de Privacidad con el objetivo de que sea fácil de entender, que confíes en ella y que sepas en todo momento qué hacemos con tus datos. Nuestro modelo de negocio no se basa en comercializar tus datos, sino en ganarnos tu confianza y ofrecerte el mejor producto posible.\n" +
                        "Nuestro propósito es trazar un nuevo camino, alineado con nuestra razón de ser como startup joven: revolucionar la forma en que se construyen las redes sociales. Ponerte a ti en el centro de todo, priorizando tu privacidad, tu control y nuestra transparencia. Explicarte con detalle qué datos necesitamos, por qué los necesitamos y qué puedes hacer tú para controlarlos. Queremos que entiendas que tú controlas tu información y que nuestra misión es ayudarte a hacerlo.\n" +
                        "Desde el primer día que imaginamos esta aplicación, nuestra meta ha sido construir un entorno seguro, privado y transparente para que puedas compartir imágenes y recuerdos con las personas que elijas, y sólo con ellas, sin miedo a un mal uso de tu información y sin que nadie pueda acceder a ella sin tu consentimiento. Queremos ser un lugar más seguro, más privado y más humano.\n" +
                        "A lo largo de esta política vamos a explicarte con detalle cómo garantizamos tu seguridad y privacidad más allá de lo que exige la ley:\n" +
                        "Qué datos recogemos y por qué lo hacemos.\n" +
                        "Para qué usamos esa información y cómo te beneficia.\n" +
                        "En qué derecho nos basamos para usar tus datos. \n" +
                        "Con quién compartimos esa información (y por qué siempre controlamos que cumplan con las leyes más estrictas).\n" +
                        "Qué pasa si tus datos viajan fuera de tu país y cómo nos aseguramos de que sigan protegidos.\n" +
                        "Durante cuánto tiempo los conservamos.\n" +
                        "Qué derechos tienes y cómo puedes ejercerlos fácilmente.\n" +
                        "Cómo protegemos tu información frente a accesos no autorizados.\n" +
                        "Cómo afecta el modelo de suscripción a tu privacidad.\n" +
                        "Por qué esta app no es para menores y cómo lo comprobamos para protegerlos.\n" +
                        "Si cambiamos algo importante en esta política, así te lo vamos a contar.\n" +
                        "Esta política está redactada siguiendo el Reglamento General de Protección de Datos (UE) 2016/679 y la Ley Orgánica 3/2018, pero hemos evitado el lenguaje excesivamente técnico para que puedas entender todo sin necesidad de conocimientos legales.\n" +
                        "Si después de leerla te queda cualquier duda, quieres aclarar algo o prefieres que borremos por completo tu información, solo tienes que escribirnos a team@fottow.com.\n" +
                        "Trataremos de responderte lo antes posible.\n" +
                        "\n" +
                        "Qué datos recopilamos y por qué\n" +
                        "Para que la aplicación funcione correctamente y pueda ofrecerte de manera segura todas las funciones que te hemos prometido, necesitamos recopilar sólo los datos estrictamente necesarios. A continuación te explicamos qué datos son, cómo los obtenemos y para qué sirven. No todos son obligatorios: algunos dependen de tu configuración y de cómo uses fottow. Estos pueden obtenerse de dos maneras:\n" +
                        "Datos proporcionados directamente por ti: cuando tú mismo los introduces o nos los envías.\n" +
                        "Correo electrónico: lo necesitamos para identificarte como usuario, para enviarte notificaciones importantes (confirmación de cuenta, avisos de seguridad, cambios en la política de privacidad) y para ayudarte a recuperar la cuenta en caso necesario. Nunca para enviarte spam ni publicidad no solicitada.\n" +
                        "Ejemplo práctico: si olvidas tu contraseña, tu email nos permite enviarte un enlace seguro para restablecerla.\n" +
                        "Imagen de perfil: para que puedas personalizar tu cuenta y que tus contactos te reconozcan fácilmente en sus círculos o chats. No es obligatorio: si lo prefieres, puedes no subir ninguna o usar una imagen genérica.\n" +
                        "Ejemplo práctico: si compartes una foto en un grupo, tu imagen de perfil ayuda a que todos sepan quién la compartió sin mostrar datos personales adicionales.\n" +
                        "Fotografías que subes: el corazón de la aplicación. Son las imágenes que decides compartir en la app. Siempre estarán bajo tu control y podrás eliminarlas cuando quieras. Las procesamos para permitirte almacenarlas y compartirlas.\n" +
                        "Ejemplo práctico: puedes subir una foto y compartir al instante esa imagen entre los integrantes de la fotografía.\n" +
                        "Datos biométricos (selfies, reconocimiento facial): información de tu rostro obtenida mediante una fotografía o selfie, que en algunos casos podemos procesar únicamente para asegurarnos de que la cuenta pertenece realmente a la persona que dices ser, evitar accesos no autorizados o que otra persona suplante tu identidad y garantizar que tu contenido está protegido. No guardamos tu “cara” en bruto como una foto normal para análisis permanentes; si se hace reconocimiento facial, los datos se transforman en patrones matemáticos cifrados imposibles de reconstruir visualmente.\n" +
                        "Ejemplo práctico: al registrarte, puedes hacernos un selfie para confirmar que eres tú y no otra persona usando tu email.\n" +
                        "Ubicación aproximada: datos de localización obtenidos de tu dispositivo, pero sólo si tú lo activas y das permiso explícito. Nos ayuda a ofrecerte funciones relacionadas con el filtrado de fotos, como etiquetar fotos con el lugar donde fueron tomadas o sugerir contenido relevante cercano. Nunca para rastrear tus movimientos ni para vender esta información a terceros. \n" +
                        "Ejemplo práctico: si compartes una foto de un evento, puedes decidir añadir automáticamente la ubicación para que otros sepan dónde fue.\n" +
                        "Datos generados automáticamente: a través de la tecnología de la aplicación que detecta, por ejemplo, cómo usas la app para mejorarla.\n" +
                        "Datos de uso, estadísticas y análisis: información sobre cómo usas la app, por ejemplo qué funciones utilizas más o menos, número de fotos compartidas o tiempo de uso. Para detectar errores, con el fin de mejorar la experiencia. Estos datos se recopilan de forma agregada y no identifican a un usuario concreto salvo que sea estrictamente necesario para resolver un problema técnico grave.\n" +
                        "Ejemplo práctico: si vemos que el 90% de los usuarios nunca encuentra una función, podemos mejorar su visibilidad.\n" +
                        "Cookies y tecnologías similares: pequeños archivos que se almacenan en tu dispositivo para mantener tu sesión iniciada o para recordar tus preferencias y que no tengas que configurarlas de nuevo. Nos permiten optimizar el rendimiento, para que no tengas que iniciar sesión cada vez que abres la app o la web y para guardar configuraciones como el idioma o el modo de visualización.\n" +
                        "Ejemplo práctico: si cambias al modo oscuro, las cookies recuerdan esta preferencia.\n" +
                        "\n" +
                        "Para qué usamos esa información\n" +
                        "Podríamos resumirlo diciendo que usamos tus datos solo para que la app funcione y sea segura, pero creemos que es mejor explicarlo con más detalle y ejemplos para que tengas total claridad.\n" +
                        "La recogida de datos tiene siempre fines concretos, legítimos y claros. En nuestro caso, estos son los principales motivos. Tu información se usa para:\n" +
                        "Permitir el registro y autenticar tu cuenta de forma segura. Necesitamos confirmar que quien crea una cuenta eres tú y no un bot o una persona que intenta hacerse pasar por ti. Para ello usamos tu email, tu contraseña y un selfie de verificación. Así podremos registrar tu cuenta, permitir que inicies sesión, que uses todas las funcionalidades de forma fluida y asegurarnos de que nadie más pueda acceder a tu cuenta.\n" +
                        "Facilitar el uso y disfrute de todas las funciones de fottow. Guardamos y mostramos tus fotos, imágenes de perfil y configuración. Sin estos datos, no podrías disfrutar de la experiencia completa. \n" +
                        "Compartir imágenes entre usuarios. Nuestra función principal es permitir recuperar fotografías que de otro modo quedarían en el olvido y facilitarte el modo en el que compartes imágenes en tu día a día. \n" +
                        "Detectar fraude o actividad sospechosa. Nuestra misión es garantizar tu seguridad (por ejemplo, con la autenticación biométrica). Los selfies y el reconocimiento facial nos ayudan a verificar que eres tú. Cuando es necesario, analizamos patrones de uso para detectar cuentas falsas, fraudes o comportamientos extraños que puedan indicar accesos no autorizados. \n" +
                        "Mejorar la experiencia y analizar el rendimiento de fottow. Analizamos datos agregados para conocer cómo interactúan los usuarios con la aplicación, para decidir qué funciones potenciar y qué problemas corregir. \n" +
                        "Cumplir nuestras obligaciones legales y de seguridad. Podemos necesitar conservar ciertos datos para responder a solicitudes legales, en caso de que una autoridad lo requiera de forma legal y justificada, siempre respetando la normativa vigente y protegiendo tu privacidad.\n" +
                        "\n" +
                        "Cómo y con quién se comparten tus fotos\n" +
                        "La naturaleza misma de esta aplicación se basa en el intercambio y disfrute de fotografías entre usuarios. Por ello todas las fotos que subas se comparten automáticamente con los usuarios de la plataforma que salgan en esas fotografías.\n" +
                        "En ningún caso tus datos se utilizarán para fines comerciales ocultos ni se venderán a terceros. No usaremos tu información con finalidades distintas a las descritas aquí sin pedir tu consentimiento previo y explícito.\n" +
                        "Limitaciones técnicas de la detección y reconocimiento facial\n" +
                        "Nuestro compromiso con tu privacidad incluye el uso de tecnología avanzada para identificar rostros en fotografías. Sin embargo, y por transparencia contigo, queremos dejar claro que esta tecnología no es infalible.\n" +
                        "Existen circunstancias que pueden impedir que un rostro se detecte correctamente, como estar de perfil, la presencia de gafas de sol, mascarillas, sombreros, cambios de iluminación, baja calidad de imagen o incluso ciertos gestos faciales. También pueden producirse falsos positivos (identificar un rostro donde no lo hay) o falsos negativos (no identificar un rostro presente en la imagen).\n" +
                        "Aunque mejoramos nuestro sistema constantemente, no podemos garantizar una detección perfecta en el 100% de los casos. Por eso, te recomendamos que revises siempre las fotos y que, si detectas cualquier error, nos lo comuniques de inmediato escribiéndonos a team@fottow.com. Esto nos ayudará a seguir perfeccionando nuestro servicio y a proteger mejor tu intimidad y la de las personas que aparecen en tus fotos.\n" +
                        "\n" +
                        "Base legal para el tratamiento\n" +
                        "En cumplimiento del Reglamento General de Protección de Datos (UE) 2016/679 (RGPD) y la Ley Orgánica 3/2018 de Protección de Datos Personales y garantía de los derechos digitales (LOPDGDD), tratamos tus datos sobre la base de las siguientes justificaciones legales:\n" +
                        "Consentimiento expreso del usuario: antes de registrar tu cuenta, te mostramos de forma clara esta Política de Privacidad y te pedimos que la aceptes. Al registrarte por tanto das tu consentimiento expreso, aceptas esta política y nos autorizas a tratar tus datos para las finalidades descritas. El consentimiento se otorga de forma libre, específica, informada e inequívoca. Puedes retirarlo cuando quieras. Desde la configuración de la app o escribiéndonos a team@fottow.com.\n" +
                        "La ejecución del contrato que aceptas al usar fottow: cuando aceptas nuestros términos y creas tu cuenta, establecemos un acuerdo: tú nos das acceso a ciertos datos, porque son necesarios para prestar el servicio que nos solicitas (sin ciertos datos, la app no puede funcionar), y nosotros te damos acceso a la plataforma y sus funcionalidades. \n" +
                        "Nuestro interés legítimo: mantener la seguridad de todos los usuarios, proteger la plataforma, prevenir abusos y mejorar nuestro servicio. Siempre valoramos si nuestro interés legítimo prevalece sobre tus derechos y libertades.\n" +
                        "\n" +
                        "Notificaciones push\n" +
                        "En Fottow Tech, S.L. queremos que siempre estés al tanto de lo que sucede en tu espacio dentro de la aplicación, especialmente cuando hay nuevas fotografías en las que podrías aparecer o cuando tus contactos han compartido contenido que pueda interesarte.\n" +
                        "Para ello, utilizamos un sistema de notificaciones push que funciona a través de Firebase Cloud Messaging (servicio prestado por Google). Este sistema asigna a tu dispositivo un identificador único denominado device token. Este token no revela tu nombre, correo electrónico ni otros datos que te identifiquen directamente, pero sí permite a la app reconocer tu dispositivo y enviarle avisos de forma selectiva.\n" +
                        "El tratamiento de este identificador se realiza únicamente con el fin de enviarte notificaciones relacionadas con:\n" +
                        "Nuevas fotografías compartidas contigo o en las que apareces.\n" +
                        "Actividad relevante.\n" +
                        "Actualizaciones importantes sobre el servicio.\n" +
                        "Puedes activar o desactivar estas notificaciones en cualquier momento desde los ajustes de tu dispositivo o de la propia aplicación. Si decides desactivarlas, la aplicación seguirá funcionando, aunque no podremos avisarte en tiempo real de este tipo de novedades.\n" +
                        "Firebase, como proveedor externo, actúa como encargado del tratamiento y cumple con la normativa europea de protección de datos (RGPD). Puedes consultar su política de privacidad aquí: https://firebase.google.com/support/privacy.\n" +
                        "\n" +
                        "Servicios y proveedores externos\n" +
                        "No podemos ofrecerte un servicio de calidad y seguro sin recurrir a algunos proveedores tecnológicos. Aquí los listamos, explicando qué hacen y cómo cumplen con la ley:\n" +
                        "Firebase / Google: autenticación de usuarios, notificaciones push, analítica de uso y almacenamiento en la nube. Certificación ISO 27001 y cumplimiento del RGPD mediante Cláusulas Contractuales Tipo.\n" +
                        "AWS (Amazon Web Services): almacenamiento seguro y escalable de datos. Cifrado en reposo y en tránsito. Centros de datos con estrictos controles de acceso.\n" +
                        "Servicios de verificación de identidad: para comprobar que eres tú, no un impostor, quien crea o accede o en casos de recuperación de cuenta. Procesamiento cifrado y eliminación de datos biométricos en cuanto dejan de ser necesarios.\n" +
                        "Proveedores de pago (si procede). Para procesar suscripciones o compras dentro de la app de forma segura. Cumplimiento de la normativa PCI-DSS.\n" +
                        "Algunos de nuestros proveedores pueden procesar datos fuera del Espacio Económico Europeo (EEE). Cuando tus datos se almacenan o procesan fuera de la UE, garantizamos el mismo nivel de protección que si estuvieran dentro. Esto se hace mediante cláusulas legales aprobadas por la Comisión Europea y auditorías de cumplimiento, tales como:\n" +
                        "Cláusulas Contractuales Tipo aprobadas por la Comisión Europea.\n" +
                        "Verificación de que el país de destino ofrece un nivel de protección de datos equivalente al europeo.\n" +
                        "Estas cláusulas garantizan que tus datos están tan protegidos como si se procesaran en España.\n" +
                        "En cualquier caso, a medida que el crecimiento de fottow nos permita disponer de los medios necesarios, trataremos de limitar nuestro uso de herramientas de terceros y de proveedores externos.\n" +
                        "\n" +
                        "Conservación de datos\n" +
                        "Usuarios activos: mientras mantengas tu cuenta activa.\n" +
                        "Usuarios inactivos: eliminamos o anonimizamos tus datos pasados 24 meses desde tu última conexión (o antes si lo pides), salvo que la ley nos obligue a conservarla más tiempo.\n" +
                        "Datos legales (facturas, pagos): 5 años según la legislación española.\n" +
                        "Datos para seguridad o fraude: tiempo necesario para cumplir con la ley o defendernos de reclamaciones. Por razones técnicas, algunas copias pueden persistir temporalmente, pero siempre bajo medidas de seguridad.\n" +
                        "\n" +
                        "Tus derechos\n" +
                        "Tú tienes el control. Puedes ejercer en cualquier momento los derechos que la ley te reconoce:\n" +
                        "Acceso a tus datos personales: saber qué datos tenemos sobre ti.\n" +
                        "Rectificación: corregir datos incorrectos o incompletos.\n" +
                        "Eliminación completa y total de tu información y datos asociados («derecho al olvido»).\n" +
                        "Limitación u oposición al tratamiento: restringir temporalmente el uso de tus datos en determinados casos.\n" +
                        "Portabilidad de tus datos a otra plataforma: solicitar una copia de tus datos en formato estructurado.\n" +
                        "Oposición: evitar que los usemos en determinadas circunstancias.\n" +
                        "Retirada de tu consentimiento: cancelar el permiso que nos diste en cualquier momento y sin justificación.\n" +
                        "Solo tienes que escribirnos a team@fottow.com indicando qué derecho quieres ejercer. Podemos pedirte que confirmes tu identidad antes de responder.\n" +
                        "\n" +
                        "Propiedad intelectual\n" +
                        "Al aceptar los Términos y Condiciones de fottow y subir fotografías o cualquier otro contenido a la aplicación, garantizas que eres consciente de que:\n" +
                        "Sólo puedes subir fotos siendo el titular de todos los derechos de propiedad intelectual sobre dicho contenido o teniendo autorización legal expresa para utilizarlo y compartirlo.\n" +
                        "Subir fotos que no te pertenecen sin permiso es una infracción legal grave.\n" +
                        "Al subir tus fotos, siguen siendo tuyas: no cedemos tu propiedad intelectual ni vendemos tu contenido.\n" +
                        "\n" +
                        "Protección de menores\n" +
                        "Nuestra comunidad está diseñada por y para adultos. No es una cuestión de discriminación hacia los menores, sino un compromiso firme con su seguridad y bienestar digital. En fottow creemos que la privacidad y la responsabilidad en internet son pilares fundamentales, y muchas de las funcionalidades que ofrecemos implican un grado de madurez y conciencia que no siempre es posible en edades tempranas.\n" +
                        "Preferimos que los menores no participen en redes sociales abiertas o masivas que, en muchos casos, pueden fomentar estereotipos poco realistas y convertirse en un escaparate de la vida personal más que en un lugar de conexión auténtica. No queremos que fottow sea un espacio más en el que los menores se vean expuestos a esas dinámicas que pueden dañar su autoestima o distorsionar la manera en la que se relacionan con los demás.\n" +
                        "Responsabilidad sobre la presencia de menores en las fotos\n" +
                        "No podemos verificar de manera automática y exacta si una fotografía contiene o no a menores de edad. Por lo tanto, la responsabilidad de identificar y proteger la imagen de menores en el contenido que compartes es exclusivamente tuya. Recuerda que compartir imágenes de menores en entornos no controlados puede suponer un riesgo para su privacidad y seguridad.\n" +
                        "Responsabilidad sobre el registro de menores\n" +
                        "Por el mismo motivo, tampoco contamos con un sistema de verificación de identidad en el registro que nos permita garantizar al 100% que un usuario es mayor de edad. Aunque nuestros Términos y Condiciones dejan claro que el servicio es sólo para adultos, dependemos en este caso de la honestidad del usuario en el momento de registrarse.\n" +
                        "Un mensaje para ti, si eres menor\n" +
                        "Si eres menor de edad, no te preocupes. Sabemos que puede ser frustrante no poder acceder a todas las funciones, pero nuestra recomendación es que disfrutes de la aplicación junto a adultos de confianza, como familiares o amigos cercanos, que sí puedan usarla. Así podrás conocer la experiencia de forma segura y adaptada a tu edad. El tiempo pasa muy rápido, y en poco tiempo podrás participar plenamente sin limitaciones, con la madurez necesaria para aprovechar todas las ventajas que nuestra comunidad ofrece. Ya lo entenderás algún día.\n" +
                        "Nuestro compromiso\n" +
                        "Priorizar la seguridad y privacidad de todos los usuarios en todo momento.\n" +
                        "Limitar el acceso de menores en la medida técnica y legal posible.\n" +
                        "Actuar de inmediato si detectamos un registro fraudulento de un menor, procediendo al cierre de la cuenta y eliminación de datos.\n" +
                        "Si sospechas que un menor está utilizando la app o tienes cualquier duda sobre este tema, escríbenos a team@fottow.com.\n" +
                        "\n" +
                        "Medidas de seguridad\n" +
                        "Aplicamos medidas técnicas avanzadas (cifrado, servidores seguros, control de accesos) y medidas organizativas (protocolos internos, formación del equipo) para evitar que tus datos se pierdan o sean utilizados sin tu permiso. No hay seguridad perfecta, pero trabajamos para acercarnos lo máximo posible:\n" +
                        "Cifrado extremo a extremo en el envío de imágenes y datos sensibles. Cifrado en tránsito y en reposo.\n" +
                        "Servidores seguros con control de acceso físico y lógico.\n" +
                        "Protecciones contra accesos no autorizados mediante autenticación reforzada.\n" +
                        "Monitorización constante para detectar usos indebidos o intentos de intrusión. Auditorías de seguridad periódicas.\n" +
                        "Políticas internas de acceso restringido únicamente a personal autorizado.\n" +
                        "Procedimientos para responder rápidamente a incidentes.\n" +
                        "\n" +
                        "Cambios en esta política\n" +
                        "Publicaremos siempre la versión más actualizada en nuestra web y dentro de la app. Si hay cambios importantes, te avisaremos antes de que entren en vigor para que puedas revisarlos. Seguiremos nuestro principio de transparencia: te diremos qué cambia y por qué.\n" +
                        "\n" +
                        "Responsable del tratamiento\n" +
                        "Fottow Tech, S.L.\n" +
                        "Domicilio Social: (28053) Madrid\n" +
                        "Email: team@fottow.com\n" +
                        "País: España\n",
            )

            //Terms and conditions
            Text(
                text = "TÉRMINOS Y CONDICIONES DE USO\n" +
                        "Última actualización: 5 de agosto de 2025\n" +
                        "Bienvenido/a a fottow. Nos alegra que quieras conocer a fondo las normas que regulan el acceso, navegación y uso de nuestra aplicación y servicios relacionados. Este documento, aunque tiene un carácter legal, está redactado para que lo entiendas con facilidad, sin tecnicismos innecesarios. Nuestro objetivo no es abrumarte con lenguaje legal incomprensible, sino explicarte de forma clara y directa qué puedes esperar de nosotros y qué esperamos de ti cuando usas fottow.\n" +
                        "Nuestra misión es ofrecer un entorno seguro, privado y controlado para compartir imágenes con otras personas, sin exponerte. No recopilamos datos tuyos para venderlos: somos un equipo que cree que la privacidad, la propiedad intelectual y el control sobre tus fotos deben ser tuyos y no nuestros.\n" +
                        "Aun así, para que todo funcione correctamente y para proteger tanto tus derechos como los de los demás usuarios, es fundamental que establezcamos unas normas claras. Usar la aplicación implica que aceptas íntegramente estos Términos y Condiciones.\n" +
                        "Introducción: por qué es importante que leas esto\n" +
                        "En Fottow Tech, S.L. (en adelante «fottow», «nosotros», «nuestro», «nos» o «la app») queremos que sepas exactamente qué puedes hacer, qué no, por qué existen estas reglas y cómo queremos que esta aplicación sea un espacio seguro, respetuoso y divertido para ti y para todos. Por ello hemos diseñado nuestros Términos de Uso con el objetivo de que puedas leerlos de principio a fin y que sean fáciles de entender. Queremos que sepas desde el primer momento:\n" +
                        "Qué puedes esperar de nosotros.\n" +
                        "Qué esperamos nosotros de ti.\n" +
                        "Qué hacemos para proteger tu privacidad y tu seguridad.\n" +
                        "Cuáles son tus derechos y nuestras responsabilidades.\n" +
                        "Que nuestro objetivo es ofrecerte un entorno seguro, privado y respetuoso para compartir tus imágenes.\n" +
                        "Que tú tienes el control de tu información y de tu experiencia dentro de la app.\n" +
                        "Que queremos que disfrutes usando la aplicación.\n" +
                        "Antes de seguir: si decides registrarte y usar fottow, significa que aceptas estas condiciones. Podrás usar nuestra app con tranquilidad y sabiendo a qué atenerte. Si no estás de acuerdo, lo mejor es que no utilices nuestro servicio. Y si tienes dudas, pregúntanos siempre: preferimos aclarar un punto que perder tu confianza.\n" +
                        "\n" +
                        "Quiénes somos\n" +
                        "Fottow Tech, S.L.\n" +
                        "Domicilio Social: 28053 Madrid (España)\n" +
                        "Correo electrónico: team@fottow.com\n" +
                        "Somos una startup española, pequeña pero ambiciosa, que nació con un objetivo: crear la app para compartir imágenes más segura y respetuosa con el usuario que existe. Para ello queremos devolver el control de las imágenes a las personas que las crean y aparecen en ellas. \n" +
                        "No dependemos de la publicidad masiva ni de la explotación indiscriminada de datos para subsistir, y eso nos permite tomar decisiones que priorizan tu privacidad por encima de cualquier otro interés. Nuestro foco es que tengas control real sobre lo que compartes. Y eso lo aplicamos incluso en la forma en la que redactamos nuestras condiciones.\n" +
                        "Como te hemos comentado, al utilizar nuestra app aceptas íntegramente estas condiciones. Cuando aceptas estos términos, estás firmando un acuerdo digital con nosotros que regula cómo puedes utilizar fottow, qué puedes esperar de nosotros y qué esperamos nosotros de ti. Este documento es un compromiso mutuo de respeto, seguridad y uso responsable.\n" +
                        "Si algo no te convence o tienes dudas, por favor contáctanos antes en team@fottow.com. Interésate por las reglas y por cómo protegemos este espacio.\n" +
                        "\n" +
                        "Qué es fottow y qué puedes hacer como usuario\n" +
                        "Fottow es una plataforma que permite compartir imágenes con otros usuarios de manera segura y privada. Eso significa que no es un escaparate abierto al mundo, sino un entorno cerrado en el que el contenido se comparte entre quienes importan. Fottow te permite:\n" +
                        "Subir las imágenes que quieras compartir.\n" +
                        "Ver fotografías tuyas que te hicieron hace años o ahora mismo. \n" +
                        "Organizar tu contenido en un espacio seguro.\n" +
                        "A diferencia de otras redes sociales:\n" +
                        "Aquí no hay muro público con tus fotos expuestas a cualquiera.\n" +
                        "Aquí no te empujamos a publicar para recibir “likes”.\n" +
                        "Aquí no usamos tus fotos para entrenar modelos de inteligencia artificial sin tu permiso.\n" +
                        "Aquí no mostramos deliberadamente tus imágenes a desconocidos para generar interacciones artificiales.\n" +
                        "Aquí no vendemos tus datos personales ni creamos perfiles comerciales para terceros.\n" +
                        "La idea es simple: compartir tu foto con aquellos con quien ya compartiste la foto. Queremos que publicar una foto no sea una decisión que luego lamentes, sino una acción segura y reversible.\n" +
                        "La app está diseñada para su uso en dispositivos móviles (Android e iOS) y, eventualmente, versión web. Algunas funciones, especialmente las relacionadas con la privacidad avanzada, estarán disponibles únicamente en la versión de pago.\n" +
                        "\n" +
                        "Quién puede usar la aplicación\n" +
                        "Fottow está pensado para personas mayores de edad (18 años o más). No es una cuestión de exclusión, sino de protección: creemos firmemente que las redes sociales tradicionales pueden llegar a fomentar estereotipos, comparaciones y contribuir a la obsesión por la imagen. No queremos que esta sea una más.\n" +
                        "¿Por qué?\n" +
                        "Porque creemos que los menores deben estar protegidos de los riesgos que conlleva compartir imágenes online.\n" +
                        "Porque no queremos fomentar dinámicas de redes sociales que a menudo afectan negativamente a la autoestima y la imagen personal de los más jóvenes.\n" +
                        "Aun así:\n" +
                        "Si eres menor y te atrae la idea que promueve fottow, te animamos a usarla junto con un adulto de confianza (padres, tutores o familiares cercanos) que gestionen tu acceso a la app.\n" +
                        "Ya lo entenderás algún día. El tiempo pasa muy rápido y esta red seguirá aquí cuando puedas usarla por ti mismo.\n" +
                        "Por otro lado, si eres un adulto y subes fotos que incluyan menores, tú eres el único responsable, debes saber que las fotos se compartirán automáticamente con el resto de personas que aparezcan en la imagen.\n" +
                        "\n" +
                        "Registro y creación de cuenta\n" +
                        "Para usar fottow, debes registrarte. Esto implica:\n" +
                        "Facilitar un correo electrónico válido. \n" +
                        "Dar datos reales y correctos. No uses identidades falsas ni suplantes a otras personas.\n" +
                        "Crear una contraseña segura.\n" +
                        "Aceptar estos términos y condiciones y la política de privacidad.\n" +
                        "No compartir tu cuenta con otras personas.\n" +
                        "No utilizar la aplicación con fines ilícitos o contrarios a la buena fe.\n" +
                        "Ejemplo: si compartes tu cuenta con un amigo y él sube contenido que infringe nuestras normas, la responsabilidad será tuya, porque la cuenta está a tu nombre.\n" +
                        "Si detectamos un uso fraudulento o sospechoso, nos reservamos el derecho de suspender temporal o definitivamente la cuenta.\n" +
                        "Eres responsable de mantener la confidencialidad de tus credenciales. Si sospechas que alguien ha accedido a tu cuenta, escríbenos inmediatamente. \n" +
                        "\n" +
                        "Uso de notificaciones push\n" +
                        "La aplicación ofrece un servicio de notificaciones push para mantenerte informado sobre nuevas fotografías, actividad de tus contactos y otras comunicaciones relevantes para el uso del servicio. Estas notificaciones se envían a través del proveedor Firebase Cloud Messaging.\n" +
                        "Al utilizar la aplicación, aceptas que podamos enviarte este tipo de avisos mientras tengas habilitada la recepción de notificaciones en tu dispositivo. En todo momento podrás desactivar esta funcionalidad desde los ajustes del sistema operativo o de la propia aplicación, entendiendo que, si lo haces, es posible que no recibas en tiempo real avisos sobre contenido o actividades importantes.\n" +
                        "El envío de notificaciones no implica el tratamiento de datos personales identificativos como tu nombre o correo, pero sí requiere el uso de un identificador técnico de tu dispositivo (device token), necesario para poder entregarlas correctamente.\n" +
                        "Nos reservamos el derecho a modificar el tipo y la frecuencia de estas notificaciones para mejorar tu experiencia de usuario o adaptarnos a cambios técnicos y legales.\n" +
                        "\n" +
                        "Privacidad y tratamiento de datos\n" +
                        "Aquí nos tomamos muy en serio tu privacidad. Por eso te pedimos que leas también nuestra Política de Privacidad, donde explicamos en detalle:\n" +
                        "Qué datos recogemos.\n" +
                        "Para qué los usamos.\n" +
                        "Cómo los protegemos.\n" +
                        "Con quién podemos compartirlos (siempre de forma controlada).\n" +
                        "Ejemplo: si subes una foto y después la borras, nosotros la eliminamos de nuestros sistemas (aunque puede haber copias temporales en caché hasta que se eliminen automáticamente).\n" +
                        "No hacemos recopilación masiva de datos con fines comerciales. Los datos que solicitamos son los estrictamente necesarios para ofrecer el servicio y mejorar tu experiencia.\n" +
                        "\n" +
                        "Lo que puedes subir (y lo que no)\n" +
                        "Al subir fotografías o cualquier otro contenido a fottow, manifiestas y garantizas que eres el titular de todos los derechos de propiedad intelectual sobre dicho contenido, que no vulneras derechos de terceros, incluidos derechos de imagen, privacidad o propiedad intelectual, o que cuentas con la autorización legal necesaria para utilizarlo y compartirlo.\n" +
                        "Tú eres el único responsable del contenido que subas. Nosotros no revisamos de forma previa todas las fotos, pero sí podremos eliminarlas si incumplen estas normas o si recibimos reclamaciones legítimas.\n" +
                        "Así pues, el usuario asume toda responsabilidad frente a Fottow Tech, S.L. y frente a terceros por cualquier reclamación relacionada con el uso no autorizado de material protegido por derechos de autor, marcas registradas u otros derechos de propiedad. Así pues, nos reservamos el derecho de eliminar cualquier contenido que infrinja derechos de terceros y de suspender la cuenta del usuario que incurra en infracciones reiteradas.\n" +
                        "Ahora bien, todo el contenido que subes a la app sigue siendo tuyo. No reclamamos la propiedad de tus imágenes ni de ningún material que subas o compartas a través de la applicación. Lo que sí nos concedes (y esto es necesario para que la aplicación funcione) es una licencia no exclusiva, mundial, gratuita y revocable para almacenar, procesar y distribuir sus fotos dentro de la app y, opcionalmente, para otros usos (como mejora del servicio, entrenamiento de IA con consentimiento…).\n" +
                        "Por tanto, puedes subir cualquier imagen de la que seas propietario o tengas derecho de uso, siempre que:\n" +
                        "No sea ilegal.\n" +
                        "No contenga contenido violento, ofensivo, sexual, discriminatorio o de odio.\n" +
                        "No vulnere la intimidad o dignidad de las personas.\n" +
                        "Como es evidente, no puedes subir:\n" +
                        "Imágenes robadas de otras personas.\n" +
                        "Material con copyright sin permiso.\n" +
                        "Contenido sexualmente explícito con menores (esto es ilegal y obviamente lo denunciamos).\n" +
                        "No vamos a usar tus fotos para campañas publicitarias sin tu permiso, no vamos a venderlas y no vamos a sublicenciarlas a terceros. Si borras una foto o tu cuenta, el contenido se eliminará de nuestros servidores (salvo las copias necesarias para cumplir con requisitos legales o de seguridad).\n" +
                        "\n" +
                        "Conducta dentro de la app\n" +
                        "Aquí viene la parte en la que te pedimos sentido común. Queremos un espacio donde todos se sientan seguros, así que no está permitido:\n" +
                        "Usar la app para acosar, amenazar o difundir bulos. Usa la app con respeto hacia los demás.\n" +
                        "Publicar imágenes para difamar o humillar a alguien.\n" +
                        "Subir imágenes ilegales, violentas, pornográficas, acosadoras, discriminatorias o que vulneren derechos de terceros.\n" +
                        "Intentar hackear, manipular o hacer ingeniería inversa de la app para ver contenido que no te pertenece.\n" +
                        "Crear cuentas falsas o suplantar la identidad de otras personas.\n" +
                        "Usar la app para fines comerciales no autorizados.\n" +
                        "En definitiva: si no lo harías delante de tu familia o en tu trabajo, probablemente no deberías hacerlo aquí.\n" +
                        "Si incumples estas normas, podremos suspender o eliminar tu cuenta sin previo aviso para proteger al resto de la comunidad.\n" +
                        "\n" +
                        "Propiedad intelectual de la aplicación\n" +
                        "Todos los derechos sobre la aplicación, su diseño, su interfaz y sus funcionalidades pertenecen a Fottow Tech, S.L. El hecho de usar la app no te concede ningún derecho sobre nuestro software, marcas o contenidos, salvo lo estrictamente necesario para utilizarla.\n" +
                        "\n" +
                        "Nuestra responsabilidad (y la tuya)\n" +
                        "Nos comprometemos a:\n" +
                        "informarte siempre si ocurre algo grave que afecte a tu cuenta o a la seguridad de tus datos.\n" +
                        "Trabajar para mantener la app funcionando de forma estable y segura.\n" +
                        "Respetar siempre tu privacidad.\n" +
                        "Informarte de cambios relevantes.\n" +
                        "Por otro lado, tú eres responsable del contenido que subes y compartes. Esto implica:\n" +
                        "Usar la app de forma legal y respetuosa.\n" +
                        "Respetar la privacidad de otras personas que aparezcan en tus fotos. Garantizas que tienes su autorización para compartirlas.\n" +
                        "Asegurarte de tener los derechos para publicar lo que subas.\n" +
                        "No publicar imágenes que puedan resultar ofensivas o ilegales.\n" +
                        "No poner en riesgo la seguridad de otros usuarios.\n" +
                        "No utilizar la app con fines comerciales no autorizados.\n" +
                        "Si alguien denuncia tu contenido, lo revisaremos. Y si incumples las normas, podremos retirarlo o bloquear tu cuenta.\n" +
                        "Precisión y limitaciones\n" +
                        "Nuestra aplicación ofrece, en determinadas funciones, la posibilidad de detectar y reconocer rostros en las fotografías.\n" +
                        "No obstante, el usuario acepta que esta tecnología, como cualquier sistema de reconocimiento facial existente en la actualidad, presenta limitaciones técnicas y no siempre detecta todos los rostros presentes en una imagen ni lo hace de forma exacta. Factores como el ángulo, la iluminación, accesorios como gafas o mascarillas, o la baja resolución pueden impedir un reconocimiento fiable.\n" +
                        "Por tanto, Fottow Tech, S.L. no será responsable de que un rostro no haya sido detectado si el fallo se debe a estas limitaciones técnicas inherentes. Es responsabilidad del usuario revisar las fotografías y, en caso de detectar un error, contactar con nuestro equipo de soporte a través de team@fottow.com para que podamos investigar y, si es posible, corregir el problema.\n" +
                        "Por otro lado, ponemos todos los medios técnicos y humanos para mantener el servicio disponible y seguro, pero no podemos garantizar que la app esté libre de errores o interrupciones en todo momento. No nos hacemos responsables de:\n" +
                        "Fallos temporales del servicio o Interrupciones técnicas inevitables.\n" +
                        "Pérdida de datos por causas fuera de nuestro control.\n" +
                        "Uso indebido de la app por parte de terceros.\n" +
                        "Contenido publicado por otros usuarios.\n" +
                        "Podemos actualizar, modificar o retirar funciones sin previo aviso, especialmente si es para mejorar la seguridad o la experiencia de usuario.\n" +
                        "\n" +
                        "Servicios de terceros\n" +
                        "La app utiliza proveedores como Firebase, Google o AWS para almacenamiento, autenticación y análisis de uso. Todos cumplen con la normativa europea de protección de datos (RGPD) y con cláusulas contractuales estándar para transferencias internacionales.\n" +
                        "En algunos casos, podríamos integrar servicios externos (por ejemplo, para pagos o verificación de identidad). Te lo comunicaremos antes de que esos servicios traten tus datos.\n" +
                        "\n" +
                        "Modificaciones del servicio\n" +
                        "Podemos añadir, cambiar o eliminar funcionalidades para mejorar la app. Si el cambio es importante y afecta al uso que haces del servicio, te avisaremos antes. Nuestro objetivo es que cualquier modificación sea siempre para mejorar tu experiencia y nunca para perjudicarte.\n" +
                        "\n" +
                        "Terminación de la cuenta\n" +
                        "Puedes cerrar tu cuenta en cualquier momento desde la configuración o escribiéndonos a team@fottow.com. Cuando cierres la cuenta, eliminaremos tus datos según lo indicado en nuestra Política de Privacidad.\n" +
                        "En casos graves de incumplimiento de las normas, podremos suspender o eliminar tu cuenta para proteger al resto de usuarios. Si eliminamos tu cuenta por incumplir estos Términos, no tendrás derecho a reembolso de suscripciones activas.\n" +
                        "\n" +
                        "Cambios en estos términos\n" +
                        "Si actualizamos estos Términos y Condiciones para adaptarlos a nuevas leyes, mejoras del servicio o cambios en la forma de operar:\n" +
                        "Si es un cambio menor, lo publicaremos en la app y seguirá vigente.\n" +
                        "Si es un cambio importante, te lo notificaremos antes dentro de la app o por email y podrás decidir si sigues usando la app.\n" +
                        "Publicaremos siempre la versión vigente y la fecha de última actualización para que sepas qué cambió y cuándo.\n" +
                        "\n" +
                        "Legislación y jurisdicción\n" +
                        "Este acuerdo se rige por la legislación española y cualquier conflicto se resolverá en los juzgados de Madrid, salvo que la ley disponga otra cosa.\n" +
                        "\n" +
                        "Contacto directo\n" +
                        "Si tienes cualquier duda sobre estos Términos y Condiciones, puedes escribirnos a team@fottow.com.\n",
            )
        }

        //Legal Advice
        Text(
            text = "AVISO LEGAL\n" +
                    "Última actualización: 5 de agosto de 2025\n" +
                    "Este Aviso Legal regula el acceso, la navegación y el uso de la aplicación fottow y, en su caso, de su versión web asociada. En él encontrarás quiénes somos, qué hacemos, cómo puedes contactar con nosotros y qué normas generales rigen el uso de nuestros servicios. \n" +
                    "Queremos que sepas que nuestro objetivo no es llenarte de lenguaje legal complicado, sino asegurarnos de que tengas muy claro quién está detrás de esta aplicación y de que estamos comprometidos con un uso responsable y seguro de nuestra plataforma.\n" +
                    "Para que tengas una idea, en el Aviso Legal incluimos apartados como:\n" +
                    "Quiénes somos y por qué existimos.\n" +
                    "Alcance de la aplicación y limitaciones de uso.\n" +
                    "Compromisos de fottow con la seguridad y la privacidad.\n" +
                    "Obligaciones detalladas del usuario.\n" +
                    "Tratamiento de contenidos y propiedad intelectual con ejemplos prácticos.\n" +
                    "Política frente a abusos y usos indebidos.\n" +
                    "Canales de contacto.\n" +
                    "\n" +
                    "Quiénes somos y qué hacemos\n" +
                    "Este Aviso Legal regula el acceso, navegación y uso de la aplicación fottow (en adelante «fottow», «nosotros», «nuestro», «nos» o «la app»), así como de su sitio web asociado y cualesquiera otros servicios o plataformas digitales bajo la titularidad de Fottow Tech, S.L., sociedad inscrita en el Registro Mercantil de Madrid, con domicilio en Madrid (España), NIF B00000000, y correo de contacto: team@fottow.com.\n" +
                    "Nuestra misión es ofrecer una forma más sencilla, rápida y segura de compartir imágenes y momentos con las personas que realmente importan. No basamos nuestro negocio en explotar los datos personales de los usuarios. \n" +
                    "Somos un equipo independiente que cree que las redes sociales pueden construirse sobre principios de respeto, privacidad y control real por parte del usuario. Este Aviso Legal no es un mero trámite legal: es nuestro compromiso escrito contigo para que sepas, desde el primer momento, qué puedes esperar de nosotros y qué esperamos nosotros de ti.\n" +
                    "\n" +
                    "Ámbito de aplicación\n" +
                    "Este Aviso Legal es aplicable a:\n" +
                    "La App fottow en cualquiera de sus versiones disponibles (Android, iOS, WebApp).\n" +
                    "El sitio web oficial de Fottow Tech y cualquier micrositio relacionado.\n" +
                    "Cualquier otro servicio, función o herramienta proporcionada directamente por nosotros que haga referencia a este Aviso Legal.\n" +
                    "El uso de nuestros servicios implica que aceptas el contenido de este Aviso Legal, así como nuestra Política de Privacidad y nuestros Términos y Condiciones de Uso. Si no estás de acuerdo con alguna de estas condiciones, te pedimos que no utilices la aplicación.\n" +
                    "\n" +
                    "Objetivo y limitaciones de uso\n" +
                    "El objetivo de fottow es facilitar un espacio digital seguro y controlado donde los usuarios puedan compartir fotografías y recuerdos. Sin embargo:\n" +
                    "No permitimos el uso de la App para actividades ilícitas, ofensivas, difamatorias, que infrinjan derechos de terceros o que supongan acoso.\n" +
                    "No está permitida la publicación, almacenamiento o intercambio de contenido que incluya material ilegal, violento, sexualmente explícito, de incitación al odio o que atente contra la dignidad de las personas.\n" +
                    "No pueden utilizar la App menores de edad. La verificación de edad y el uso adecuado recaen también sobre el usuario que crea la cuenta.\n" +
                    "\n" +
                    "Compromisos de Fottow Tech\n" +
                    "Nos comprometemos a:\n" +
                    "Mantener actualizadas las medidas técnicas y organizativas necesarias para proteger tu información.\n" +
                    "Informarte siempre de forma clara sobre cambios relevantes en nuestras políticas o condiciones de uso.\n" +
                    "Respetar en todo momento tus derechos como usuario, en especial tu derecho a la privacidad y a decidir con quién compartes tus contenidos.\n" +
                    "No modificar las reglas de juego sin avisarte con antelación suficiente.\n" +
                    "\n" +
                    "Obligaciones del usuario\n" +
                    "El usuario se compromete a:\n" +
                    "Utilizar la aplicación conforme a la ley, la moral y el orden público.\n" +
                    "Usar la App únicamente para fines personales y legítimos.\n" +
                    "Mantener la confidencialidad de sus credenciales de acceso.\n" +
                    "Configurar correctamente sus preferencias de privacidad, especialmente en lo relativo a compartir imágenes con terceros.\n" +
                    "Asegurarse de que cuenta con el consentimiento necesario de padres o tutores cuando suba fotografías en las que aparecen menores.\n" +
                    "Respetar los derechos de propiedad intelectual e industrial de terceros.\n" +
                    "No utilizar cuentas falsas, suplantar identidades ni falsificar datos para acceder a la App.\n" +
                    "No realizar actos que puedan dañar la imagen, intereses o derechos de Fottow Tech, S.L. o de terceros.\n" +
                    "\n" +
                    "Contenidos y propiedad intelectual\n" +
                    "De la aplicación:\n" +
                    "Todos los contenidos, marcas, logotipos, diseños, interfaces y código fuente de la aplicación son propiedad de Fottow Tech, S.L., o bien contamos con autorización para su uso.\n" +
                    "Queda prohibida su reproducción, distribución o modificación sin nuestro consentimiento expreso.\n" +
                    "Del usuario:\n" +
                    "Todo el contenido que subas a la App (fotografías, textos, comentarios) seguirá siendo de tu propiedad.\n" +
                    "Al subirlo, nos concedes una licencia no exclusiva, mundial, gratuita y revocable para almacenar, procesar y distribuir sus fotos dentro de la app.\n" +
                    "Garantizas que las imágenes que compartes son tuyas o que tienes derechos suficientes para hacerlo.\n" +
                    "Reconoces que las imágenes compartidas podrán ser visibles para las personas que aparecen en ellas de manera automática.\n" +
                    "\n" +
                    "Política frente a abusos y usos indebidos\n" +
                    "Podemos suspender o eliminar tu cuenta si:\n" +
                    "Subes contenido que infringe la ley o nuestras normas de conducta.\n" +
                    "Usas la App para acosar, difamar o perjudicar a otros usuarios.\n" +
                    "Intentas manipular o vulnerar la seguridad de nuestros sistemas.\n" +
                    "\n" +
                    "Enlaces y servicios de terceros\n" +
                    "La App puede contener enlaces o integraciones con servicios de terceros (almacenamiento en la nube, herramientas de verificación…). Estos enlaces se facilitan únicamente como información y no implican que recomendemos esos sitios. No nos hacemos responsables del contenido o de las prácticas de privacidad de dichos terceros. Te recomendamos leer sus políticas antes de interactuar con ellos.\n" +
                    "\n" +
                    "Exención de responsabilidad\n" +
                    "Fottow Tech, S.L. no será responsable de:\n" +
                    "Daños causados por el mal uso de la aplicación: no nos hacemos responsables de la pérdida de datos derivada de errores del usuario, fallos técnicos o incidencias ajenas a nuestro control.\n" +
                    "Problemas técnicos que impidan el acceso a nuestros servicios: no podemos garantizar que la App esté libre de errores o interrupciones en todo momento, aunque trabajamos para que así sea.\n" +
                    "Contenidos generados por los propios usuarios.\n" +
                    "La descarga y el uso de la App se hacen bajo tu propia responsabilidad.\n" +
                    "\n" +
                    "Legislación y jurisdicción\n" +
                    "Este Aviso Legal se rige por la legislación española. Cualquier disputa se someterá a los juzgados y tribunales de Madrid, salvo que la ley disponga otra cosa.\n" +
                    "\n" +
                    "Cambios en este Aviso Legal\n" +
                    "Nos reservamos el derecho de actualizar o modificar este Aviso Legal cuando sea necesario para adaptarnos a cambios legales, técnicos o de negocio. Siempre publicaremos la versión más reciente en la App y te avisaremos de los cambios más relevantes.\n" +
                    "\n" +
                    "Contacto\n" +
                    "Para cualquier duda o incidencia relacionada con este Aviso Legal o con el uso de la App, puedes escribirnos a:\n" +
                    "Correo: team@fottow.com\n" +
                    "Dirección: Madrid (España)\n"
        )

        //Cookies Policy
        Text(
            text = "POLÍTICA DE COOKIES\n" +
                    "Última actualización: 5 de agosto de 2025\n" +
                    "Sabemos que el término “cookies” puede sonar anticuado, aburrido, técnico o incluso sospechoso dependiendo del usuario. Pero debemos hablar de ellas y por eso queremos explicarte de forma clara y sencilla qué son, para qué las usamos y cómo puedes controlarlas en cualquier momento, para que sepas exactamente qué ocurre cada vez que aceptas (o rechazas) nuestras cookies.\n" +
                    "Para que te hagas una idea, en esta Política de cookies hablamos de:\n" +
                    "Cada tipo de cookie con ejemplos concretos de cómo se usan en la app.\n" +
                    "Ejemplos de cookies propias y de terceros que realmente se usan en apps similares.\n" +
                    "Qué implicaciones tiene desactivarlas en la versión gratuita y en la de pago.\n" +
                    "Cómo gestionarlas en Android, iOS y navegadores web.\n" +
                    "Información sobre almacenamiento de datos generados por cookies y tiempos de conservación.\n" +
                    "\n" +
                    "\n" +
                    "Qué son las cookies y por qué te lo explicamos\n" +
                    "En Fottow Tech, S.L. (en adelante «fottow», «nosotros», «nuestro», «nos» o «la app») queremos explicarte de forma clara y honesta cómo utilizamos las cookies y tecnologías similares para que puedas decidir con conocimiento si quieres aceptarlas, limitarlas o eliminarlas. \n" +
                    "Una cookie no es más que un pequeño archivo que tu dispositivo guarda, cuando navegas por una web o usas una aplicación que las utiliza, para recordar tus preferencias o reconocer que eres tú cuando vuelves. No son programas dañinos, y no tienen acceso directo a la información de tu dispositivo. \n" +
                    "Sirven para que la web o la app “recuerde” cierta información sobre ti o sobre tu uso, como tu idioma preferido, las páginas que has visitado o si ya has iniciado sesión. Nos ayudan a recordar tus preferencias, a mantener tu sesión iniciada y a entender cómo usas nuestra aplicación para poder mejorar tu experiencia de usuario.\n" +
                    "Lo importante: no todas las cookies son malas ni espían tu vida privada. Pero sí creemos que es fundamental que sepas exactamente:\n" +
                    "Qué cookies usamos.\n" +
                    "Para qué las usamos.\n" +
                    "Durante cuánto tiempo permanecen activas.\n" +
                    "Y cómo puedes controlarlas o eliminarlas.\n" +
                    "Nuestro compromiso es explicártelo con un lenguaje claro, porque si algo afecta a tu privacidad, mereces entenderlo de principio a fin.\n" +
                    "\n" +
                    "Por qué usamos cookies\n" +
                    "Usamos cookies para que nuestra App y nuestro sitio web funcionen correctamente, pero también para que tu experiencia sea más rápida, cómoda y segura. En concreto:\n" +
                    "Recordar tu inicio de sesión para que no tengas que introducir tu usuario y contraseña cada vez que accedes.\n" +
                    "Guardar tus preferencias de idioma, configuración de privacidad y modo de visualización.\n" +
                    "Analizar de forma anónima el uso que haces de la App para mejorarla continuamente y adaptarla a lo que realmente necesitas.\n" +
                    "Ayudarnos a detectar problemas técnicos y solucionarlos.\n" +
                    "Protegerte contra fraudes o accesos no autorizados detectando actividad inusual.\n" +
                    "Mostrarte contenido o funciones relevantes según tus intereses y tu actividad previa.\n" +
                    "\n" +
                    "Tipos de cookies que utilizamos\n" +
                    "Para que sepas exactamente qué hace cada una, las clasificamos así:\n" +
                    "Cookies técnicas o estrictamente necesarias: son imprescindibles para que la App funcione. Sin ellas, algunas funciones dejarían de estar disponibles (por ejemplo, mantener la sesión iniciada o cargar tu biblioteca de fotos).\n" +
                    "Cookies de personalización: guardan ajustes como idioma, región o formato de visualización para que no tengas que reconfigurarlo cada vez.\n" +
                    "Cookies de análisis o medición: nos permiten conocer cómo interactúan los usuarios con la App: qué pantallas visitan, cuánto tiempo pasan en ellas, si hay fallos técnicos… Esto nos ayuda a detectar problemas y mejorar tu experiencia. \n" +
                    "Cookies de seguridad: ayudan a prevenir abusos, fraudes o accesos no autorizados. Son parte esencial de nuestras medidas de protección.\n" +
                    "Cookies publicitarias (solo como posibilidad en la versión gratuita): nos permiten mostrarte anuncios relevantes. No vendemos tu información a terceros, pero los anunciantes pueden recibir datos anonimizados para medir la eficacia de sus campañas, aunque en nuestra filosofía preferimos reducir al mínimo la publicidad intrusiva.\n" +
                    "Algunas funciones de la App pueden depender de proveedores externos (como Google Firebase, Amazon Web Services para almacenar y analizar datos). Estos terceros pueden establecer sus propias cookies con fines que escapan a nuestro control directo, aunque les exigimos cumplir el RGPD y, en caso de transferir datos fuera del Espacio Económico Europeo, aplican garantías como las Cláusulas Contractuales Tipo aprobadas por la UE.\n" +
                    "\n" +
                    "Dónde se almacenan los datos de las cookies\n" +
                    "Queremos que sepas también qué pasa con la información que guardan las cookies:\n" +
                    "Las cookies se almacenan localmente en tu dispositivo (móvil, tablet o ordenador).\n" +
                    "Algunas contienen identificadores únicos que permiten que nuestros servidores o servicios externos reconozcan tu sesión o preferencias.\n" +
                    "En algunos casos (por ejemplo, cookies de autenticación o analítica), esos identificadores pueden enviarse (siempre de forma anonimizada o pseudonimizada) a nuestros servidores o a proveedores de servicios en la nube (por ejemplo, Firebase o Google Analytics) para analizar el uso de la app o mantener tu cuenta iniciada.\n" +
                    "Nunca se almacenan en lugares inseguros ni se venden a terceros.\n" +
                    "Tratamos de evitar enviar datos de comportamiento a terceros no esenciales y el uso de cookies se limita al mínimo necesario para que la App funcione y para mantener tus preferencias. Esta es una de las formas en las que materializamos nuestro compromiso de privacidad: menos cookies, más control para ti.\n" +
                    "\n" +
                    "Cuánto tiempo permanecen activas\n" +
                    "Cookies de sesión: se eliminan automáticamente cuando cierras la App o el navegador.\n" +
                    "Cookies persistentes: permanecen en tu dispositivo durante un tiempo determinado (por ejemplo, 30 días).\n" +
                    "Puedes borrarlas en cualquier momento desde los ajustes de tu dispositivo.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Cómo puedes controlarlas o eliminarlas\n" +
                    "Puedes aceptar o rechazar todas las cookies, o bien configurarlas una por una desde la configuración de tu navegador o desde nuestro panel de control de cookies. Porque debes saber que tienes derecho a decidir qué cookies aceptas y cuáles no. Puedes:\n" +
                    "Configurar tu navegador o dispositivo para bloquear o eliminar cookies.\n" +
                    "Utilizar las opciones de configuración de la propia App para ajustar tus preferencias de privacidad.\n" +
                    "Desactivar la instalación de cookies de terceros desde sus propias políticas.\n" +
                    "En cualquier caso, ten en cuenta que, si desactivas ciertas cookies esenciales, es posible que la App no funcione correctamente, que algunas funciones de la app no estén disponibles o que tengas que iniciar sesión manualmente cada vez.\n" +
                    "Cómo gestionar y eliminar cookies en tu dispositivo\n" +
                    "No queremos solo decirte “puedes desactivarlas” y dejarte a medias. Queremos que sepas cómo hacerlo exactamente, paso a paso, según el dispositivo que uses.\n" +
                    "En Android (app o navegador móvil)\n" +
                    "Abre Ajustes en tu dispositivo Android.\n" +
                    "Busca la sección Privacidad o Configuración del navegador (por ejemplo, en Chrome: Ajustes > Configuración del sitio > Cookies).\n" +
                    "Desde ahí, bloquea, permite o elimina cookies según prefieras.\n" +
                    "Si usas fottow, revisa también los ajustes internos para limitar cookies de terceros o de análisis.\n" +
                    "En iOS (iPhone o iPad)\n" +
                    "Ve a Ajustes en tu iPhone/iPad.\n" +
                    "Desplázate hasta Safari (o tu navegador preferido).\n" +
                    "Activa la opción Bloquear todas las cookies o ajusta la opción de Prevención de seguimiento inteligente.\n" +
                    "Si quieres eliminar cookies existentes, ve a Borrar historial y datos de sitios web.\n" +
                    "En navegadores web (PC/Mac)\n" +
                    "Google Chrome: Menú > Configuración > Privacidad y seguridad > Cookies y otros datos de sitios.\n" +
                    "Mozilla Firefox: Menú > Ajustes > Privacidad y seguridad > Cookies y datos del sitio.\n" +
                    "Safari (Mac): Preferencias > Privacidad > Gestionar datos de sitios web.\n" +
                    "Microsoft Edge: Configuración > Cookies y permisos de sitio.\n" +
                    "Aceptación, rechazo o retirada del consentimiento\n" +
                    "Cuando entras por primera vez en la app o web, te damos la opción de aceptar todas las cookies o configurarlas. Si cambias de opinión, puedes volver a ajustar o eliminar cookies siguiendo los pasos anteriores.\n" +
                    "\n" +
                    "Cambios en esta política\n" +
                    "Podemos actualizar esta Política de Cookies en cualquier momento para adaptarla a cambios legales, técnicos o de negocio. Siempre publicaremos la versión más reciente y, si hay cambios relevantes, te lo notificaremos de forma clara en la app y en la web antes de que entren en vigor..\n" +
                    "\n" +
                    "Contacto\n" +
                    "Si tienes cualquier duda sobre el uso de cookies en nuestra App o web, puedes escribirnos a:\n" +
                    "Correo: team@fottow.com\n" +
                    "Dirección: Madrid (España)\n" +
                    "\n" +
                    "\n"
        )
    }
}