/* 
 * File:   leveltopng.cpp
 * Author: Algunenano
 *
 * Created on March 03, 2012, 4:31 AM
 */
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <vector>
#include <png.h>
#include <getopt.h>

#include "leveltopng.hpp"

using namespace std;

void imgDeleter (img *d)
{
    if (d->colorbuffer != nullptr) free(d->colorbuffer);
    delete(d);
}

int File2Buffer (std::string _route, img& _buf)
{
    string ln;
    ifstream fl (_route);
    unsigned int linepos = 0;
    long int x, y, val, beg, end, snd;
    int ret = 0;
    
    char *endptr = NULL;
    
    /* File doesn't exist, or not accessible*/
    if (!fl.is_open()){
        fprintf(stderr,"Couldn't open read location\n");
        return -1;
    }
    
    /* Get ride of initial comments */
    do{
        getline(fl,ln);
        linepos++;
        if ((ln.length() > 0) && (ln[0] != CHAR_COMMENT)) break;
    } while (fl.good());
    
    if (! ln.length() > 0){
        fl.close();
        return -1;
    }
    
 /* INITIAL DECLARATION (SIZE) contained in ln*/
    shared_ptr<vector <string> > vs(new vector<string>);
    unsigned long int ll, length, i;
    for (ll = 0, i = 0; i < ln.length(); i++){
        if (ln[i]==' '){
            length = i -ll;
            if (length<=0) ll=i+1;
            else {
                string part = string (ln,ll,length);
                (*vs).push_back(part);
                ll = i+1;
            }
        }
    }
    
    if (i != ll)
    {
        length = i -ll;
        string part = string (ln,ll,i-ll);
        (*vs).push_back(part);
    }

    
    if ((vs->size() != 4) || ((*vs)[0][0] != CHAR_SIZE))
    {
        fprintf(stderr,"Line %u: Invalid size\n",linepos);
        ret = -1;
        goto finalise;
    }
    
    /* Check size arguments x,y,default */    
    x = strtol((*vs)[1].data(),&endptr,0);
    if ((*endptr != '\0') || (x <= 0))
    {
        fprintf(stderr,"Line %u: Invalid argument (width) \n",linepos);
        ret = -1;
        goto finalise;
    }
    
    y = strtol((*vs)[2].data(),&endptr,0);    
    if ((*endptr != '\0') || (y <= 0))
    {
        fprintf(stderr,"Line %u: Invalid argument (height) \n",linepos);
        ret = -1;
        goto finalise;
    }
    
    val = strtol((*vs)[3].data(),&endptr,0);    
    if ((*endptr != '\0') || (val < 0) || (val > 0xFFFFFF))
    {
        fprintf(stderr,"Line %u: Invalid argument (value) \n",linepos);
        ret = -1;
        goto finalise;
    }
    
    /* Variables processed => Creation!*/
    _buf.height = y;
    _buf.width = x;
    
    _buf.colorbuffer = (long int*) malloc (sizeof(long int) * x * y);
    
    if (_buf.colorbuffer == nullptr)
    {
        fprintf(stderr,"Line %u: Error allocating memory \n",linepos);
        ret = -1;
        goto finalise;
    }
    for (i = 0; i < (unsigned long) x * y; i++) 
        (_buf.colorbuffer)[i] = val;
    
  /* FROM NOW ON PARSE EVERY LINE LOOKING FOR COLUMNS, ROWS OR CELLS */
    do {   
        getline(fl,ln);
        linepos++;
        
        if ((ln.length() == 0) || (ln[0] == CHAR_COMMENT)) continue;
        
        /* Line breaking */
        vs->clear();
        for (ll = 0, i = 0; i < ln.length(); i++){
            if (ln[i]==' '){
                length = i -ll;
                if (length<=0) ll=i+1;
                else {
                    string part = string (ln,ll,length);
                    (*vs).push_back(part);
                    ll = i+1;
                }
            }
        }

        if (i != ll)
        {
            length = i -ll;
            string part = string (ln,ll,i-ll);
            (*vs).push_back(part);
        }
        
        /* Line checking */
        if (vs->size() <= 0) continue;
        if ((vs->size() <= 2) || (vs->size() > 5))
        {
            fprintf(stderr,"Line %u: Bad declaration (ignored!) \n",linepos);
            continue;
        }
        
        val = strtol((*vs)[1].data(),&endptr,0);    
        if ((*endptr != '\0') || (val < 0) || (val > 0xFFFFFF))
        {
            fprintf(stderr,"Line %u: Invalid argument (value) ignored! \n",linepos);
            continue;
        }
        
        snd = strtol((*vs)[2].data(),&endptr,0);    
        if ((*endptr != '\0') || (snd < 0))
        {
            fprintf(stderr,"Line %u: Invalid argument (2 arg) ignored! \n",linepos);
            continue;
        }
        
        switch ((*vs)[0][0])
        {
            case (CHAR_CELL):
            {
                if (vs->size() != 4)
                {
                    fprintf(stderr,"Line %u: Missing one operand: ignored! \n",linepos);
                    continue;
                }
                
                if (snd >= _buf.width)
                {
                    fprintf(stderr,"Line %u: Invalid argument (column) ignored! \n",linepos);
                    continue;
                }
                
                /* One more parameter */
                y = strtol((*vs)[3].data(),&endptr,0);    
                if ((*endptr != '\0') || (y <= 0) || (y >= _buf.height))
                {
                    fprintf(stderr,"Line %u: Invalid argument (height) ignored! \n",linepos);
                    continue;
                }
                
                /* Mark cell */
                _buf.colorbuffer[_buf.width * y + snd] = val;           
                
                break;
            }
            case (CHAR_COLUMN):
            {
                if (snd >= _buf.width)
                {
                    fprintf(stderr,"Line %u: Invalid argument (column) ignored! \n",linepos);
                    continue;
                }
                
                //Optional values?
                if (vs->size() != 5)
                    end = _buf.height;
                else
                {
                    end = strtol((*vs)[4].data(),&endptr,0);
                    if ((*endptr != '\0') || (end < 0) || (end >= _buf.height))
                    {
                        fprintf(stderr,"Line %u: Invalid argument (height) ignored! \n",linepos);
                        continue;
                    }
                }
                
                if (vs->size() < 4)
                    beg = 0;
                else
                {
                    beg = strtol((*vs)[3].data(),&endptr,0);
                    if ((*endptr != '\0') || (beg < 0) || (beg > end))
                    {
                        fprintf(stderr,"Line %u: Invalid argument (height) ignored! \n",linepos);
                        continue;
                    }
                }
                
                /* Mark values */
                for (i = beg; i < (unsigned long) end; i++)
                    _buf.colorbuffer[_buf.width * i + snd] = val;
                
                break;
            }
            case (CHAR_ROW):
            {
                if (snd >= _buf.height)
                {
                    fprintf(stderr,"Line %u: Invalid argument (row) ignored! \n",linepos);
                    continue;
                }
                
                //Optional values?
                if (vs->size() != 5)
                    end = _buf.width;
                else
                {
                    end = strtol((*vs)[4].data(),&endptr,0);
                    if ((*endptr != '\0') || (end < 0) || (end >= _buf.width))
                    {
                        fprintf(stderr,"Line %u: Invalid argument (width) ignored! \n",linepos);
                        continue;
                    }
                }
                
                if (vs->size() < 4)
                    beg = 0;
                else
                {
                    beg = strtol((*vs)[3].data(),&endptr,0);
                    if ((*endptr != '\0') || (beg < 0) || (beg > end))
                    {
                        fprintf(stderr,"Line %u: Invalid argument (width) ignored! \n",linepos);
                        continue;
                    }
                }
                
                /* Mark values */
                for (i = beg; i < (unsigned long) end; i++)
                    _buf.colorbuffer[_buf.width * snd + i] = val;
                
                break;
            }

            default:
            {
                fprintf(stderr,"Line %u: Bad declaration (ignored!) \n",linepos);
                continue;
            }
        }
        
    } while (fl.good());
    
    

 finalise:
    fl.close();
    return ret;    
   
}

inline void setRGB(png_byte *ptr, long int val)
{
    ptr[0] = val / 256 / 256 % 256;
    ptr[1] = val / 256 % 256;
    ptr[2] = val % 256;
}


int writeimage (string _route, img& _buf)
{
    int ret = 0;
    
    FILE *fl = fopen(_route.c_str(), "wb");
    if (fl == NULL){
        fprintf(stderr,"Couldn't open destiny location\n");
        return -1;
    }
    
    png_structp pngp = png_create_write_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
    png_infop infop = NULL;
    png_bytep row = NULL;
    
    if (pngp == NULL)
    {
        fprintf(stderr, "Error allocating psp\n");
        ret = -1;
        goto finalise;
    }
    
    infop = png_create_info_struct(pngp);
    if (infop == NULL)
    {
        fprintf(stderr, "Error allocating infop\n");
        ret = -1;
        goto finalise;
    }
    
    // Setup Exception handling
    if (setjmp(png_jmpbuf(pngp))) {
            fprintf(stderr, "Error during png creation\n");
            ret = -1;
            goto finalise;
    }
    
    png_init_io(pngp, fl);
    
    /* Write header */
    png_set_IHDR(pngp, infop, _buf.width, _buf.height,
                    8, PNG_COLOR_TYPE_RGB, PNG_INTERLACE_NONE,
                    PNG_COMPRESSION_TYPE_BASE, PNG_FILTER_TYPE_BASE);
    
    png_text titletext;
    titletext.compression = PNG_TEXT_COMPRESSION_NONE;
    titletext.key = "Title";
    titletext.text = "leveltopng";
    png_set_text(pngp, infop, &titletext, 1);
    
    png_write_info(pngp, infop);
    
    /* Writing stuff into image */
    row = (png_bytep) malloc(_buf.width * 3 * sizeof(png_byte));
    
    int x, y;
    for (y = 0 ; y < _buf.height; y++)
    {
        for (x = 0; x < _buf.width; x++)
        {
            setRGB(&(row[x*3]), _buf.colorbuffer[y*_buf.width + x]);
        }
        png_write_row(pngp, row);
    }                         
                                    
 
    png_write_end(pngp, NULL);
 finalise:
    
    fclose (fl);
    // FREE ALL MEMORY!
    if (row != NULL) free (row);
    if (infop != NULL)
    {
        png_destroy_info_struct (pngp, &infop );
        png_free_data(pngp, infop, PNG_FREE_ALL, -1);
    }
    if (pngp != NULL) png_destroy_write_struct(&pngp, (png_infopp)NULL);
    
 
    return ret;
    
}

const char* G_stropts = "hf:o:";
static struct option G_longopts[] =
{
    	{ "help",       no_argument,            0,      'h'},
	{ "output",     optional_argument,      0,      'o'},
        { "file",       required_argument,      0,      'f'},
	{ NULL, 0, NULL, 0 }
};

void print_help(char *name)
{
    printf("Usage: %s [OPTIONS]\n\
Options:\n\
  -f, --file=INPUT\t\tselects the input file with the description (Required)\n\
  -o, --output=FILE\t\tselects where to put the output (Default: ${input}.png)\n\
  -h, --help\t\t\tshows this list\n",
            name);
}



int main (int argc, char** argv)
{
    string inputfile = "";
    string outputfile = "";
    
    int optc;
    while ((optc = getopt_long (argc, argv, G_stropts, G_longopts, NULL))!=-1){
        switch (optc)
        {
            case 'h':
                print_help(argv[0]);
                exit(EXIT_SUCCESS);
            case 'o':
            {
                outputfile.assign(optarg);
                break;
            }
            case 'f':
            {
                inputfile.assign(optarg);
                break;
            }
            default:
                print_help(argv[0]);
                exit(EXIT_SUCCESS);
        }
    }
    
    if (inputfile.length() == 0)
    {
        fprintf(stderr,"Input file required\n");
        print_help(argv[0]);
        exit(EXIT_FAILURE);
    }
    
    if (outputfile.length() == 0)
        outputfile = inputfile + ".png";
    
    
    
    shared_ptr<struct img> buf (new struct img, imgDeleter);
    
    
    if ((File2Buffer(inputfile, buf.operator *()) != 0) || (buf->colorbuffer == nullptr))
    {
        fprintf(stderr,"Error reading file, or something\n");
        exit(EXIT_FAILURE);
    }
    
    writeimage (outputfile, buf.operator *());
    
    return (EXIT_SUCCESS);
}