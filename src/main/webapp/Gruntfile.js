module.exports = function( grunt ) {

    // set task config
    grunt.initConfig({
    	jshint : {
            options: {
                curly: false,
                eqeqeq: false,
                eqnull: false,
                browser: false,
                sub: true,
                smarttabs: true,
                asi: true,
                globals: {
                    jQuery: true
                },
            },
    	    all : ['js/app.js']
    	},

    	// concat options
	    concat : {
	        task: {
	            src: [ 'css/normalize.css', 'css/style.css' ],
	            dest: 'css/production.css'
	        }
	    },

	    cssmin: {
            task : {
                options : {
                    report : 'gzip'
                },
                files : { 
                    'css/production.min.css': [ 'css/production.css' ]
                }
            }
        },

        // js minifcation options
        uglify : {
            task : {
                options : {
                    report : 'gzip',
                    compress : true,
                    mangle : false,
                    preserveComments: false
                },
                files : {
                    'js/app.min.js' : [
                        'js/jquery.js',
                    	'js/app.js'
                    ]
                }
            }
        },

	    watch : {
			js : {
				files : [ 
                    'js/app.js'
                ],
				tasks : [ 'uglify' ]
			},
            css : {
                files : [ 
                    'css/*.css'
                ],
                tasks : [ 'ccat', 'build' ]  
            }
		}
    });

    // load plugins
    //grunt.loadNpmTasks( 'plugin' );

    // set custom tasks
    //grunt.registerTask( 'default', ['task'] );

    grunt.loadNpmTasks( 'grunt-contrib-jshint' );
    grunt.loadNpmTasks( 'grunt-contrib-cssmin' );
    grunt.loadNpmTasks( 'grunt-contrib-uglify' );
    grunt.loadNpmTasks( 'grunt-contrib-watch'  );
    grunt.loadNpmTasks( 'grunt-contrib-concat' );

    grunt.registerTask( 'default', [ 'watch' ] );
    grunt.registerTask( 'build', [ 'cssmin' ] );
    grunt.registerTask( 'ccat', [ 'concat' ] );
    grunt.registerTask( 'ugly', [ 'uglify'] );
    grunt.registerTask( 'hint', [ 'jshint'] );

    
}