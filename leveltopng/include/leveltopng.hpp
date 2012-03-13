/* 
 * File:   leveltopng.hpp
 * Author: Algunenano
 *
 * Created on March 12, 2012, 4:35 AM
 */

#ifndef LEVELTOPNG_HPP
#define	LEVELTOPNG_HPP

#include <string>
#include <memory>

/* Definition of various characters */
#define CHAR_COMMENT    '#'
#define CHAR_SIZE       'S'
#define CHAR_COLUMN     'C'
#define CHAR_ROW        'R'
#define CHAR_CELL       'X'

struct img {
        /* size should be width * heigh */
        /* Saved by rows (1 long int = 1 pixel) */
    long int* colorbuffer;
    int width;
    int height;
    
};

void imgDeleter (img *d);

int File2Buffer (std::string _route, img* buf);

int writeimage (std::string _route, img& _buf);


#endif	/* LEVELTOPNG_HPP */

