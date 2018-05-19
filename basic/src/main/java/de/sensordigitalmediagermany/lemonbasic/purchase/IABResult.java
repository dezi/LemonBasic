package de.sensordigitalmediagermany.lemonbasic.purchase;

public class IABResult
{
    private int response;
    private String message;

    public IABResult(int response, String message)
    {
        this.response = response;

        if ((message == null) || (message.trim().length() == 0))
        {
            this.message = IabHelper.getResponseDesc(response);
        }
        else
        {
            this.message = message + " (response: " + IabHelper.getResponseDesc(response) + ")";
        }
    }

    public int getResponse()
    {
        return response;
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isSuccess()
    {
        return response == IabHelper.BILLING_RESPONSE_RESULT_OK;
    }

    public boolean isFailure()
    {
        return !isSuccess();
    }

    public String toString()
    {
        return "IABResult: " + getMessage();
    }
}

