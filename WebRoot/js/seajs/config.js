
seajs.config({

    plugins: ['shim'],
    
    alias: {
        'jquery': {
            src: 'http://libs.baidu.com/jquery/1.9.0/jquery.min.js',
            exports: 'jQuery'
        },
        'validate': {
            src: 'lib/jquery-validation/jquery.validate.min.js',
            deps: ['jquery','lib/jquery-validation/jquery.validate.css','lib/jquery.metadata.js'],
            exports: 'validate'
        },
		'powerFloat':{
			src:'lib/jquery-powerFloat/jquery-powerFloat.js',
			deps:['jquery','lib/jquery-powerFloat/powerFloat.css'],
			exports:'powerFloat'
		}
    
    },
    
    paths: {
        'gallery': 'https://a.alipayobjects.com/gallery'
    },
    
    debug: true,
	base: '/js/seajs',
})
