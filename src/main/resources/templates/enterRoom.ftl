
{"result": ${lr.result} <#rt/>
<#if lr.cause?exists>
, "cause": "${lr.cause}" <#rt/>
</#if>
<#if lr.onlines?exists>
, "data": [
<#list lr.onlines as online>
{ <#rt/>
        "user": "${online.user}" <#rt/>
} <#rt/>
<#if online?has_next>,</#if>
</#list>
]
</#if>
}

