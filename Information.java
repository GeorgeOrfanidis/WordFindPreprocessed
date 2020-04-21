public class Information implements Comparable<Information>
{
    
    int row;
    int column;
    int directionX;
    int directionY;
    String str;
    
    public int compareTo( Information foo) {return this.str.compareTo( foo.str);}
    
    Information()
    {
        row=0;
        column=0;
        directionX=0; 
        directionY=0; 
        str=""; 
    }
    void SetRow(int rpara)
    {
        row=rpara;
    }
    void SetColumn(int cpara)
    {
        column=cpara;
    }
    void SetDX(int dxpara)
    {
        directionX=dxpara;
    }
    void SetDY(int dypara)
    {
        directionY=dypara;
    }
    void SetString(String spara)
    {
        str=spara;
    }
    int GetRow()
    {
        return row; 
    }
    int GetColumn()
    {
        return column; 
    }
    int GetDX()
    {
        return directionX ; 
    }
    int GetDY()
    {
        return directionY ; 
    }
    String Direction(int directionXPara, int directionYPara)
    {
        String direction[]={"North","East","South","West","SouthEast","NorthWest","SouthWest","NorthEast"};
        if(directionXPara==0 && directionYPara==1)
        {
            return direction[1];
        }
        else if(directionXPara==1 && directionYPara==0)
        {
            return direction[2];
        }
        else if(directionXPara==1 && directionYPara==1)
        {
            return direction[4];
        }
        else if(directionXPara==0 && directionYPara==-1)
        {
            return direction[3];
        }
        else if(directionXPara==1 && directionYPara==-1)
        {
            return direction[6];
        }
        else if(directionXPara==-1 && directionYPara==0)
        {
            return direction[0];
        }
        else if(directionXPara==-1 && directionYPara==1)
        {
            return direction[7];
        }
        else 
        return direction[5];
       
    }
    String GetString()
    {
        return str; 
    }
}

