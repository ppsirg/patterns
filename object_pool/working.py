

def saludar(*nombres, **opciones):
    nombre = ', '.join(nombres)
    frases = {
        'normal': 'hola',
        'tierno': 'holi',
        'grosero': 'tos que'
    }
    if 'modo' in opciones:
        prefijo = frases[opciones['modo']]
    else:
        prefijo = frases['normal']
    return f'{prefijo} {nombre}'


print(
    saludar(
        'cristian', 'camilo', 'laura', 'jose',
        modo='tierno'
        )
    )